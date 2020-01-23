package NetworkVis;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Observer {
    public abstract void update(ArrayList<Node> nodes, ArrayList<ArrayList<Integer>> adjList) throws IOException;
}
