package inversequadratic;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultObj {
    private ArrayList<HashMap> process;
    private double result;

    public ResultObj() {
    }

    public ResultObj(ArrayList<HashMap> process) {
        this.process = process;
    }

    public ResultObj(ArrayList<HashMap> process, double result) {
        this.process = process;
        this.result = result;
    }

    public ArrayList<HashMap> getProcess() {
        return process;
    }

    public void setProcess(ArrayList<HashMap> process) {
        this.process = process;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

}
