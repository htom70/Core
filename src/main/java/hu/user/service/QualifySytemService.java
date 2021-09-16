package hu.user.service;

import fraud_detection_web_service.Qualifiers;
import fraud_detection_web_service.SpecifyQualifiersDateInterval;
import hu.user.domain.Fraud;
import hu.user.repository.FraudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualifySytemService {

    private FraudRepository fraudRepository;

    @Autowired
    public QualifySytemService(FraudRepository fraudRepository) {
        this.fraudRepository = fraudRepository;
    }

    public Qualifiers getQualifiers(SpecifyQualifiersDateInterval interval) {
        List<Fraud> predictedAndObservedFraudsFromRepository = fraudRepository.findObservedAndPredictedFrauds();
        int truePositive = 0;
        int falsePositive = 0;
        int falseNegative = 0;
        int trueNegative = 0;
        double precision = 0.0;
        double recall = 0.0;
        double specifity = 0.0;
        double accuracy = 0.0;
        double f1Score = 0.0;

        for (Fraud item : predictedAndObservedFraudsFromRepository) {
            int predictedValue = item.getPredictedValue();
            int observedValue = item.getObservedValue();
            if (observedValue == 0 & predictedValue == 0) {
                trueNegative++;
            } else if (observedValue == 0 & predictedValue == 1) {
                falseNegative++;
            } else if (observedValue == 1 & predictedValue == 0) {
                falsePositive++;
            } else if (observedValue == 1 & predictedValue == 1) {
                truePositive++;
            }
        }
        precision = (double) truePositive / (truePositive + falsePositive);
        recall = (double)truePositive / (truePositive + falseNegative);
        specifity = (double)trueNegative / (trueNegative + falsePositive);
        accuracy = (double)(truePositive + trueNegative) / (truePositive + trueNegative + falsePositive + falseNegative);
        f1Score = 2 * precision * recall / (precision + recall);
        Qualifiers qualifiers = new Qualifiers(truePositive, falsePositive, falseNegative, trueNegative, precision, recall, specifity, accuracy, f1Score);
        return qualifiers;
    }
}
