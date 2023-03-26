package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private int label;
    private List<Node> neighbors = new ArrayList<>();

    public Node (int label) {
        this.label = label;
    }
    public void addNeighbour (Node node) {
        neighbors.add(node);
    }
    public List<Node> getNeighbours() {
        return neighbors;
    }
    public int getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.label + "";
    }

    @Override
    public boolean equals(Object obj) {
        return this.label == ((Node) obj).label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
