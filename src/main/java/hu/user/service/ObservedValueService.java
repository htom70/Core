package hu.user.service;

import hu.user.domain.Fraud;
import hu.user.repository.FraudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservedValueService {

    private FraudRepository fraudRepository;

    @Autowired
    public ObservedValueService(FraudRepository fraudRepository) {
        this.fraudRepository = fraudRepository;
    }

    public void saveObservedValue(String transactionId, int value) {
        Fraud fraud = fraudRepository.findByTransactionId(transactionId);
        fraud.setObservedValue(value);
        fraud.setObserved(true);
        fraudRepository.save(fraud);
    }
}
