package hu.user.service;

import hu.user.config.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncSinglePrediction {

    private RestConfig restConfig;
    private RestTemplate restTemplate;

    @Autowired
    public AsyncSinglePrediction(RestConfig restConfig) {
        restTemplate = restConfig.getRestTemplate(new RestTemplateBuilder());
    }

//    public CompletableFuture<Response> getAsyncPrediction(Request request, URI estimatorURI) {
//        CompletableFuture<Response> result = new CompletableFuture<>();
//        new Thread(() -> {
//            Map<String, List<Number>> requestMap = new HashMap<>();
//            List<Number> requestParameters = new ArrayList<>();
//            requestParameters.add(request.getTime());
//            requestParameters.add(request.getV1());
//            requestParameters.add(request.getV2());
//            requestParameters.add(request.getV3());
//            requestParameters.add(request.getV4());
//            requestParameters.add(request.getV5());
//            requestParameters.add(request.getV6());
//            requestParameters.add(request.getV7());
//            requestParameters.add(request.getV8());
//            requestParameters.add(request.getV9());
//            requestParameters.add(request.getV10());
//            requestParameters.add(request.getV11());
//            requestParameters.add(request.getV12());
//            requestParameters.add(request.getV13());
//            requestParameters.add(request.getV14());
//            requestParameters.add(request.getV15());
//            requestParameters.add(request.getV16());
//            requestParameters.add(request.getV17());
//            requestParameters.add(request.getV18());
//            requestParameters.add(request.getV19());
//            requestParameters.add(request.getV20());
//            requestParameters.add(request.getV21());
//            requestParameters.add(request.getV22());
//            requestParameters.add(request.getV23());
//            requestParameters.add(request.getV24());
//            requestParameters.add(request.getV25());
//            requestParameters.add(request.getV26());
//            requestParameters.add(request.getV27());
//            requestParameters.add(request.getV28());
//            requestParameters.add(request.getAmount());
//            requestMap.put("values", requestParameters);
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<Map<String, List<Number>>> httpEntity = new HttpEntity<>(requestMap, httpHeaders);
//            ResponseEntity<ResponseFromEstimator> responseEntity = restTemplate.postForEntity(estimatorURI, httpEntity, ResponseFromEstimator.class);
//            ResponseFromEstimator responseFromEstimator = responseEntity.getBody();
//            Response response = new Response();
//            response.setPrediction(responseFromEstimator.getPrediction());
//            response.setNegativeProbability(responseFromEstimator.getNegativeProbability());
//            response.setPositiveProbability(responseFromEstimator.getPositiveProbability());
//            result.complete(response);
//        }).start();
//        return result;
//    }
}
