package hu.user;

import fraud_detection_web_service.*;
import hu.user.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class WebserviceEndpoint {
    private static final String NAMESPACE_URI = "http://fraud_detection_web_service";

    private VoteModul voteModul;
    private AsyncVoteModul asyncVoteModul;
    private EstimatorHandler estimatorHandler;
    private ObservedValueService observedValueService;
    private QualifySytemService qualifySytemService;

    @Autowired
    public WebserviceEndpoint(VoteModul voteModul, AsyncVoteModul asyncVoteModul, EstimatorHandler estimatorHandler,
                              ObservedValueService observedValueService, QualifySytemService qualifySytemService) {
        this.voteModul = voteModul;
        this.asyncVoteModul = asyncVoteModul;
        this.estimatorHandler = estimatorHandler;
        this.observedValueService = observedValueService;
        this.qualifySytemService = qualifySytemService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "generateDetectionRequest")
    @ResponsePayload
    public GenerateDetectionResponse getPrediction(@RequestPayload GenerateDetectionRequest generateDetectionRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        DetectionResponse detectionResponse = voteModul.voting(generateDetectionRequest);
        GenerateDetectionResponse generateDetectionResponse = objectFactory.createGenerateDetectionResponse();
        generateDetectionResponse.setDetectionResponse(detectionResponse);
        return generateDetectionResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "availableEstimatorsAndWeightsRequest")
    @ResponsePayload
    public AvailableEstimatorsAndWeightsResponse getEstimatorsAndWeights() {
        List<Estimator> estimators = estimatorHandler.getAvailableEstimators();
        AvailableEstimatorsAndWeightsResponse availableEstimatorsAndWeightsResponse = new AvailableEstimatorsAndWeightsResponse(estimators);
        return availableEstimatorsAndWeightsResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveObservedValueRequest")
    @ResponsePayload
    public SaveObservedValueResponse saveObservedValue(@RequestPayload SaveObservedValueRequest saveObservedValueRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        ObservedFraudRequest observedFraudRequest = saveObservedValueRequest.getRequest();
        observedValueService.saveObservedValue(observedFraudRequest.getId(), observedFraudRequest.getValue());
        SaveObservedValueResponse saveObservedValueResponse = objectFactory.createSaveObservedValueResponse();
        ObservedFraudResponse observedFraudResponse = objectFactory.createObservedFraudResponse();
        observedFraudResponse.setResult("OK");
        saveObservedValueResponse.setResponse(observedFraudResponse);
        return saveObservedValueResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "generateQualifiersRequest")
    @ResponsePayload
    public GenerateQualifiersResponse generateQualifiers(@RequestPayload GenerateQualifiersRequest generateQualifiersRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        GenerateQualifiersResponse generateQualifiersResponse = objectFactory.createGenerateQualifiersResponse();
        SpecifyQualifiersDateInterval interval = generateQualifiersRequest.getInterval();
        Qualifiers qualifiers = qualifySytemService.getQualifiers(interval);
        generateQualifiersResponse.setResponse(qualifiers);
        return generateQualifiersResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "configureEstimatorWeightsRequest")
    @ResponsePayload
    public ConfigureEstimatorWeightsResponse configureEstimatorWeights(@RequestPayload ConfigureEstimatorWeightsRequest configureEstimatorWeightsRequest) {
        List<EstimatorWeights> estimatorWeights = configureEstimatorWeightsRequest.getEstimatorWeights();
        List<Estimator> estimatorsAndWeights = estimatorHandler.configEstimatorWeights(estimatorWeights);
        ConfigureEstimatorWeightsResponse configureEstimatorWeightsResponse = new ConfigureEstimatorWeightsResponse(estimatorsAndWeights);
        return configureEstimatorWeightsResponse;
    }
}