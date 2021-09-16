package hu.user.service;

import hu.user.config.EstimatorConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncVoteModul {

    private EstimatorConfig estimatorConfig;
    private AsyncSinglePrediction asyncSinglePrediction;

    @Autowired
    public AsyncVoteModul(EstimatorConfig estimatorConfig, AsyncSinglePrediction asyncSinglePrediction) {
        this.estimatorConfig = estimatorConfig;
        this.asyncSinglePrediction = asyncSinglePrediction;
    }

//    public Response voting(Request request) {
//        long startAsyncVoteTime = System.currentTimeMillis();
//        List<Estimator> estimators = estimatorConfig.getEstimators();
//        List<Response> predictions = new ArrayList<>();
//        Map<Response, Integer> predictionsAndWeights = new HashMap<>();
//        Estimator estimator0 = estimators.get(0);
//        URI estimatorRestUri0 = null;
//        try {
//            estimatorRestUri0 = new URI(estimator0.getUri());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        CompletableFuture<Response> asyncResponse0 = asyncSinglePrediction.getAsyncPrediction(request, estimatorRestUri0);
//
//        Estimator estimator1 = estimators.get(1);
//        URI estimatorRestUri1 = null;
//        try {
//            estimatorRestUri1 = new URI(estimator1.getUri());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        CompletableFuture<Response> asyncResponse1 = asyncSinglePrediction.getAsyncPrediction(request, estimatorRestUri1);
//
//
//        Estimator estimator2 = estimators.get(2);
//        URI estimatorRestUri2 = null;
//        try {
//            estimatorRestUri2 = new URI(estimator2.getUri());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        CompletableFuture<Response> asyncResponse2 = asyncSinglePrediction.getAsyncPrediction(request, estimatorRestUri2);
//
//        Estimator estimator3 = estimators.get(3);
//        URI estimatorRestUri3 = null;
//        try {
//            estimatorRestUri3 = new URI(estimator3.getUri());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        CompletableFuture<Response> asyncResponse3 = asyncSinglePrediction.getAsyncPrediction(request, estimatorRestUri3);
//
//        List<Response> asyncResponses = Stream.of(asyncResponse0, asyncResponse1, asyncResponse2, asyncResponse3)
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList());
//
//        predictionsAndWeights.put(asyncResponses.get(0), estimator0.getWeight());
//        predictionsAndWeights.put(asyncResponses.get(1), estimator1.getWeight());
//        predictionsAndWeights.put(asyncResponses.get(2), estimator2.getWeight());
//        predictionsAndWeights.put(asyncResponses.get(3), estimator3.getWeight());
//        long endAsyncVoteTime = System.currentTimeMillis();
//        long elapsedAsysncVoteTime = endAsyncVoteTime - startAsyncVoteTime;
//        System.out.println("Aszinkron predikciós összidő: " + elapsedAsysncVoteTime + " ms");
//        return predictionsEvaluate(predictionsAndWeights);
//    }

//    private Response predictionsEvaluate(Map<Response, Integer> predictionsAndWeights) {
//        Response summaryzedResponse = new Response();
//        int noOKNumber = 0;
//        int OKNumber = 0;
//        double noOKProbability = 1;
//        double OKProbability = 1;
//        for (Map.Entry<Response, Integer> entry : predictionsAndWeights.entrySet()) {
//            if (entry.getKey().getPrediction() == 1) {
//                noOKNumber += entry.getValue();
//                noOKProbability *= entry.getKey().getPositiveProbability();
//            } else {
//                OKNumber += entry.getValue();
//                OKProbability *= entry.getKey().getNegativeProbability();
//            }
//        }
//        if (OKNumber > noOKNumber) {
//            summaryzedResponse.setPrediction(0);
//            summaryzedResponse.setNegativeProbability(OKProbability);
//            summaryzedResponse.setPositiveProbability(1 - OKProbability);
//        } else if (OKNumber < noOKNumber) {
//            summaryzedResponse.setPrediction(1);
//            summaryzedResponse.setPositiveProbability(noOKProbability);
//            summaryzedResponse.setNegativeProbability(1 - noOKProbability);
//        }
//        return summaryzedResponse;
//    }

}
