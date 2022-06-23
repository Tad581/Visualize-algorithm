package Algorithm.Step.CreateStep;

import Algorithm.Step.Step;
// import Algorithm.Step.Pseudo.Pseudo;
import Algorithm.Step.Pseudo.Pseudo;

public class CreatePseudoStep implements CreateStep{
    @Override
    public Step CreateOneStep() {
        // TODO Auto-generated method stub
        return new Pseudo();
    }
}
