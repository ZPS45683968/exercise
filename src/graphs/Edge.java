package graphs;

import java.util.Objects;

public class Edge {
    private Node from;  // source node
    private Node to; // destination node

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }
    @Override
    public String toString() {
        return "(" + from + "," + to + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(from, ((Edge) obj).from) && Objects.equals(to, ((Edge) obj).to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}