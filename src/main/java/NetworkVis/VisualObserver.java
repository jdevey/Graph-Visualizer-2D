package NetworkVis;

import java.util.ArrayList;

public class VisualObserver extends Observer {

    private GraphCanvas graphCanvas;

    VisualObserver(GraphCanvas graphCanvas) {
        this.graphCanvas = graphCanvas;
    }

    @Override
    public void update(ArrayList<Node> nodes, ArrayList<ArrayList<Integer>> adjList) {
        graphCanvas.render(nodes, adjList);
    }
}
