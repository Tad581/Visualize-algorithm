package Algorithm.Step.CreateStep;

import Algorithm.Step.Step;
import Algorithm.Step.Detail.Detail;

public class CreateDetailStep implements CreateStep{

    @Override
    public Step CreateOneStep() {
        // TODO Auto-generated method stub
        return new Detail();
    }
    
}
