package application.Algorithm.Step.CreateStep;

import application.Algorithm.Step.Step;
// import Algorithm.Step.Pseudo.Pseudo;
import application.Algorithm.Step.Pseudo.Pseudo;

public class CreatePseudoStep implements CreateStep{
    @Override
    public Step CreateOneStep() {
        // TODO Auto-generated method stub
        return new Pseudo();
    }
}
