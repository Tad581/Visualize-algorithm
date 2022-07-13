package application.Algorithm.Step.Pseudo;

import application.Algorithm.Step.Step;

public class Pseudo extends Step {
    private int StepID;

    public Pseudo() {

    }

    public Pseudo(int StepID) {
        this.StepID = StepID;
    }

    public int getStepID() {
        return StepID;
    }

    public void setStepID(int stepID) {
        StepID = stepID;
    }

}
