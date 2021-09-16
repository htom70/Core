package hu.user.service;

import fraud_detection_web_service.DetectionRequest;
import fraud_detection_web_service.DetectionResponse;
import fraud_detection_web_service.Estimator;
import fraud_detection_web_service.GenerateDetectionRequest;
import hu.user.config.EstimatorConfig;
import hu.user.domain.Fraud;
import hu.user.repository.FraudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteModul {

    private EstimatorConfig estimatorConfig;
    private SinglePrediction singlePrediction;
    private FraudRepository fraudRepository;

    @Autowired
    public VoteModul(EstimatorConfig estimatorConfig, SinglePrediction singlePrediction, FraudRepository fraudRepository) {
        this.estimatorConfig = estimatorConfig;
        this.singlePrediction = singlePrediction;
        this.fraudRepository = fraudRepository;
    }

    public DetectionResponse voting(GenerateDetectionRequest generateDetectionRequest) {
        long startVoteTime = System.currentTimeMillis();
        DetectionRequest detectionRequest = generateDetectionRequest.getDetectionRequest();
        List<Estimator> estimators = estimatorConfig.getEstimators();
//        List<Response> predictions = new ArrayList<>();
        Map<DetectionResponse, Integer> predictionsAndWeights = new HashMap<>();
        for (Estimator estimator : estimators) {
            URI estimatorRestUri = null;
            try {
                estimatorRestUri = new URI(estimator.getEstimatorURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            predictionsAndWeights.put(singlePrediction.getPrediction(detectionRequest, estimatorRestUri), estimator.getEstimatorWeight());
        }
        long endVoteTime = System.currentTimeMillis();
        long elapsedTime = endVoteTime - startVoteTime;
        System.out.println("Szinkron predikciós összidő: "+elapsedTime+" ms");
        DetectionResponse summaryzedResponse= predictionsEvaluate(predictionsAndWeights);
        Fraud predictedFraud = new Fraud();
        predictedFraud.setPredictedValue(summaryzedResponse.getPrediction());
        predictedFraud.setPredicted(true);
        predictedFraud.setAmount(detectionRequest.getAmount());
        predictedFraud.setPositiveProbability(summaryzedResponse.getPositiveProbability());
        predictedFraud.setNegativeProbability(summaryzedResponse.getNegativeProbability());
        Integer status = generateDetectionRequest.getStatus();
        if (status != null) {
            predictedFraud.setObservedValue(status);
            predictedFraud.setObserved(true);
        }
        fraudRepository.save(predictedFraud);
        return summaryzedResponse;
    }

    private DetectionResponse predictionsEvaluate(Map<DetectionResponse, Integer> predictionsAndWeights) {
        DetectionResponse summaryzedResponse = new DetectionResponse();
        int noOKNumber = 0;
        int OKNumber = 0;
        double noOKProbability = 1;
        double OKProbability = 1;
        for (Map.Entry<DetectionResponse, Integer> entry : predictionsAndWeights.entrySet()) {
            if (entry.getKey().getPrediction() == 1) {
                noOKNumber += entry.getValue();
                noOKProbability *= entry.getKey().getPositiveProbability();
            } else {
                OKNumber += entry.getValue();
                OKProbability *= entry.getKey().getNegativeProbability();
            }
        }
        if (OKNumber > noOKNumber) {
            summaryzedResponse.setPrediction(0);
            summaryzedResponse.setNegativeProbability(OKProbability);
            summaryzedResponse.setPositiveProbability(1-OKProbability);
        } else if (OKNumber < noOKNumber) {
            summaryzedResponse.setPrediction(1);
            summaryzedResponse.setPositiveProbability(noOKProbability);
            summaryzedResponse.setNegativeProbability(1-noOKProbability);
        }
        return summaryzedResponse;
    }
}
