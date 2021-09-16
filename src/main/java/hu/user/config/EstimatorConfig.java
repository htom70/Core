package hu.user.config;

import fraud_detection_web_service.Estimator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class EstimatorConfig {

    @Bean
    public List<Estimator> getEstimators() {
        List<Estimator> estimators = new ArrayList<>();
        estimators.add(new Estimator("randomforest", 1, "http://localhost:8081/api/v1/resources/predict_and_proba"));
        estimators.add(new Estimator("gradientboosting", 1,"http://localhost:8082/api/v1/resources/predict_and_proba"));
        estimators.add(new Estimator("logisticregression", 1,"http://localhost:8083/api/v1/resources/predict_and_proba"));
        estimators.add(new Estimator("watson", 1,"http://localhost:8084/api/v1/resources/predict_and_proba"));
        estimators.add(new Estimator("LightGBM", 1,"http://localhost:8085/api/v1/resources/predict_and_proba"));
        return estimators;
    }
}
