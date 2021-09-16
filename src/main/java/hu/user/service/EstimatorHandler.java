package hu.user.service;

import fraud_detection_web_service.Estimator;
import fraud_detection_web_service.EstimatorWeights;
import hu.user.config.EstimatorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstimatorHandler {

    private EstimatorConfig estimatorConfig;

    private List<Estimator> estimators;

    @Autowired
    public EstimatorHandler(EstimatorConfig estimatorConfig) {
        this.estimatorConfig = estimatorConfig;
        estimators = getAvailableEstimators();
    }

    public List<Estimator> getAvailableEstimators() {
        return estimatorConfig.getEstimators();
    }

    public List<Estimator> configEstimatorWeights(List<EstimatorWeights> estimatorWeights) {
        for (Estimator estimator : estimators) {
            for (EstimatorWeights estimatorWeight : estimatorWeights) {
                if (estimator.getEstimatorName().equals(estimatorWeight.getName())) {
                    estimator.setEstimatorWeight(estimatorWeight.getWeight());
                }
            }
        }
        return estimators;
    }
}
