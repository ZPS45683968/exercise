package graphs;

public class GraphParser {
    /*
    The string format is as follows:
    -First line: the number of nodes in the graph
    -Second line: the number of edges in the graph
    -Each remaining line specifies a single edge in the graph, as a space-separated pair of numbers
     */
    public static Node[] parseGraph(String spec) {
        Node[] nodes = null;
        String[] lines = spec.split("['\n',' ']"); // split on newlines and spaces

        int numNodes = Integer.parseInt(lines[0]);  // first line is number of nodes
        int numEdges = Integer.parseInt(lines[1]);  // second line is number of edges

        nodes = new Node[numNodes]; //  create nodes

        for (int i = 0; i < numNodes; i++) { //create nodes
            nodes[i] = new Node(i+1);
        }
        for (int i = 0; i < numEdges; i++) { //add edges
            int node1 = Integer.parseInt(lines[2*i+2]);
            int node2 = Integer.parseInt(lines[2*i+3]);
            nodes[node1-1].addNeighbour(nodes[node2-1]);
        }
        return nodes;
    }
}
