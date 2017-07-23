package dh_design_model.expression;

/**
 * 包含解释器外的全局信息
 * Created by dell on 2016/6/19.
 */
public class Context {
    private String input;
    private String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
