package application.Algorithm.Step.Detail;

import application.Algorithm.Step.Step;

public class Detail extends Step{
    private int FromVerID;
    private int ToVerID;
    private boolean isCutVer = false;

    public Detail(){
        
    }

    public Detail(int from,int to){
        this.FromVerID = from;
        this.ToVerID = to;
    }

    public int getFromVerID() {
        return FromVerID;
    }

    public void setFromVerID(int fromVerID) {
        FromVerID = fromVerID;
    }

    public int getToVerID() {
        return ToVerID;
    }

    public void setToVerID(int toVerID) {
        ToVerID = toVerID;
    }

    public boolean isCutVer() {
        return isCutVer;
    }

    public void setCutVer(boolean isCutVer) {
        this.isCutVer = isCutVer;
    }

    
}
