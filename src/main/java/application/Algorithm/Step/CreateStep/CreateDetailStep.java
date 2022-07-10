package application.Algorithm.Step.CreateStep;

import application.Algorithm.Step.Step;
import application.Algorithm.Step.Detail.Detail;

public class CreateDetailStep implements CreateStep{

    @Override
    public Step CreateOneStep() {
        // TODO Auto-generated method stub
        return new Detail();
    }
    
}
