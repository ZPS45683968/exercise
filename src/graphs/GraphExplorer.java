package graphs;
import java.util.HashSet;
import java.util.Set;
public class GraphExplorer {
    public static Set<Edge> listEdges (Node[] nodes) {
        Set<Edge> edges = new HashSet<Edge>();  // set of edges
        for (Node node : nodes) {  // for each node
            for (Node to : node.getNeighbours()) {   // for each neighbour
                edges.add(new Edge(node, to));   // add edge to set
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        String spec = "3\n" +
                "4\n" +
                "1 2\n" +
                "2 3\n" +
                "3 2\n" +
                "3 1";
        Set<Edge> edges = listEdges(GraphParser.parseGraph(spec));
        System.out.println(edges);
    }
}
