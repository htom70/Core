package hu.user.domain;

public enum FraudPredictionValues {

    NO_FRAUD("OK",1),
    FRAUD("NoOK",0);

    private String state;
    private int value;

    FraudPredictionValues(String state, int value) {
        this.state = state;
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static FraudPredictionValues valueOfCurrent(int v) {
        for (FraudPredictionValues f : values()) {
            if (f.value==v) {
                return f;
            }
        }
        return null;
    }


}
