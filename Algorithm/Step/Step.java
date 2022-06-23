package Algorithm.Step;

public abstract class Step {
    private String StepContent;

    public String getStepContent() {
        return StepContent;
    }

    public void setStepContent(String stepContent) {
        StepContent = stepContent;
    }
    
    @Override
    public String toString() {
        String str = StepContent;
        return str;
    }
}
