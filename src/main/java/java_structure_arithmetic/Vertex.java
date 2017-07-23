package java_structure_arithmetic;

/**
 * 图
 * Vertex : 顶点
 * Created by lx on 2016/10/4.
 */
public class Vertex {
    private char label;
    private boolean wasVisit;

    public Vertex(char label) {
        this.label = label;
        this.wasVisit = false;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public boolean isWasVisit() {
        return wasVisit;
    }

    public void setWasVisit(boolean wasVisit) {
        this.wasVisit = wasVisit;
    }
}
