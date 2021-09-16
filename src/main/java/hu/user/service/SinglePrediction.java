package hu.user.service;

import fraud_detection_web_service.DetectionRequest;
import fraud_detection_web_service.DetectionResponse;
import hu.user.config.RestConfig;
import hu.user.domain.ResponseFromEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Service
public class SinglePrediction {

    private RestConfig restConfig;
    private RestTemplate restTemplate;

    @Autowired
    public SinglePrediction(RestConfig restConfig) {
        restTemplate = restConfig.getRestTemplate(new RestTemplateBuilder());
    }

    public DetectionResponse getPrediction(DetectionRequest detectionRequest, URI estimatorURI) {
        Map<String, List<Object>> requestMap = new HashMap<>();
        List<Object> requestParameters = new ArrayList<>();
        //TO-DO requestParameters beállításe
        requestParameters.add(detectionRequest.getCardNumber());
        requestParameters.add(detectionRequest.getTransactionType());
        requestParameters.add(detectionRequest.getTimestamp());
        requestParameters.add(detectionRequest.getAmount());
        requestParameters.add(detectionRequest.getCurrencyName());
        requestParameters.add(detectionRequest.getResponseCode());
        requestParameters.add(detectionRequest.getCountryName());
        requestParameters.add(detectionRequest.getVendorCode());
        requestParameters.add(detectionRequest.getAmount());
        requestMap.put("values", requestParameters);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String,List<Object>>> httpEntity = new HttpEntity<>(requestMap, httpHeaders);
        ResponseEntity<ResponseFromEstimator> responseEntity = restTemplate.postForEntity(estimatorURI,  httpEntity, ResponseFromEstimator.class);
        ResponseFromEstimator responseFromEstimator = responseEntity.getBody();
        DetectionResponse detectionResponse = new DetectionResponse();
        detectionResponse.setPrediction(responseFromEstimator.getPrediction());
        detectionResponse.setNegativeProbability(responseFromEstimator.getNegativeProbability());
        detectionResponse.setPositiveProbability(responseFromEstimator.getPositiveProbability());
        return detectionResponse;
    }
}
