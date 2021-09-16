package hu.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fraud {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String transactionId;
    protected int time;

    protected double amount;

    private int predictedValue;

    private int observedValue;

    private boolean isPredicted;

    private boolean isObserved;

    private double positiveProbability;

    private double negativeProbability;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String fraudId) {
        this.transactionId = fraudId;
    }

    public int getPredictedValue() {
        return predictedValue;
    }

    public void setPredictedValue(int predictedValue) {
        this.predictedValue = predictedValue;
    }

    public int getObservedValue() {
        return observedValue;
    }

    public void setObservedValue(int observedValue) {
        this.observedValue = observedValue;
    }

    public boolean isPredicted() {
        return isPredicted;
    }

    public void setPredicted(boolean predicted) {
        isPredicted = predicted;
    }

    public boolean isObserved() {
        return isObserved;
    }

    public void setObserved(boolean observed) {
        isObserved = observed;
    }

    public double getPositiveProbability() {
        return positiveProbability;
    }

    public void setPositiveProbability(double positiveProbability) {
        this.positiveProbability = positiveProbability;
    }

    public double getNegativeProbability() {
        return negativeProbability;
    }

    public void setNegativeProbability(double negativeProbability) {
        this.negativeProbability = negativeProbability;
    }
}
