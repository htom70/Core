package hu.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFromEstimator implements Serializable {

    protected int prediction;
    protected double negativeProbability;
    protected double positiveProbability;

    public ResponseFromEstimator() {
    }

    public ResponseFromEstimator(int prediction, double negativeProbability, double positiveProbability) {
        this.prediction = prediction;
        this.negativeProbability = negativeProbability;
        this.positiveProbability = positiveProbability;
    }

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    public double getNegativeProbability() {
        return negativeProbability;
    }

    public void setNegativeProbability(double negativeProbability) {
        this.negativeProbability = negativeProbability;
    }

    public double getPositiveProbability() {
        return positiveProbability;
    }

    public void setPositiveProbability(double positiveProbability) {
        this.positiveProbability = positiveProbability;
    }
}
