package NetworkVis;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphCanvas extends JPanel {
    private ArrayList<Node> nodes;
    private ArrayList<ArrayList<Integer>> adjList;

    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw edges
        for (int i = 0; i < nodes.size(); ++i) {
            ArrayList <Integer> adj = adjList.get(i);
            Node a = nodes.get(i);
            for (Integer integer : adj) {
                Node b = nodes.get(integer);

                g.setColor(Color.white);
                g.setStroke(new BasicStroke(Constants.EDGE_WIDTH));
                g.drawLine((int) (double) a.getX(), (int) (double) a.getY(), (int) (double) b.getX(), (int) (double) b.getY());

                GradientPaint gradient = new GradientPaint(
                        (int) (double) a.getX(), (int) (double) a.getY(), a.getColor(),
                        (int) (double) b.getX(), (int) (double) b.getY(), b.getColor());
                g.setPaint(gradient);
                g.setStroke(new BasicStroke(Constants.EDGE_WIDTH - Constants.EDGE_BORDER_WIDTH));
                g.drawLine((int) (double) a.getX(), (int) (double) a.getY(), (int) (double) b.getX(), (int) (double) b.getY());
            }
        }

        // Draw nodes
        for (Node node : nodes) {
            g.setColor(Color.white);
            g.fillOval((int)Math.round(node.getX()) - Constants.NODE_DIAMETER / 2,
                    (int)Math.round(node.getY()) - Constants.NODE_DIAMETER / 2,
                    Constants.NODE_DIAMETER, Constants.NODE_DIAMETER);
            g.setColor(node.getColor());
            g.fillOval((int)Math.round(node.getX() - (Constants.NODE_DIAMETER - Constants.NODE_BORDER_WIDTH * 2) / 2),
                    (int)Math.round(node.getY() - (Constants.NODE_DIAMETER - Constants.NODE_BORDER_WIDTH * 2) / 2),
                    Constants.NODE_DIAMETER - Constants.NODE_BORDER_WIDTH * 2,
                    Constants.NODE_DIAMETER - Constants.NODE_BORDER_WIDTH * 2);
        }

        Font font = new Font("TimesRoman", Font.BOLD, 22);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        g.setFont(font);

        // Draw labels
        for (Node node : nodes) {
            double x = node.getX() + (double)Constants.NODE_DIAMETER / 2 + 2;
            double y = node.getY() - fontMetrics.getHeight() / 2 + fontMetrics.getAscent() - 1;
            g.setPaint(Color.black);
            g.drawString(node.getLabel(), (int)x, (int)y);
            g.setPaint(Color.white);
            g.drawString(node.getLabel(), (int)x - 1, (int)y - 1);
        }
    }

    void render(ArrayList<Node> nodes, ArrayList<ArrayList<Integer>> adjList) {
        this.nodes = nodes;
        this.adjList = adjList;
        repaint();
    }
}
