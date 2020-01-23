package NetworkVis;

import java.io.IOException;
import java.util.ArrayList;

import static NetworkVis.Constants.DAMPING_CONST;
import static NetworkVis.PhysicsUtils.calculateDistance;

class Graph {
    private ArrayList<Node> nodes;
    private ArrayList <ArrayList <Integer>> adjList;
    private ArrayList<Observer> observers;

    Graph(ArrayList<Node> nodes, ArrayList <ArrayList <Integer>> adjList) {
        this.nodes = nodes;
        this.adjList = adjList;
        observers = new ArrayList<>();
    }

    void updateNodes() throws IOException {
        for(Node node: nodes) {
            node.setX(node.getX() + (node.getVx() * DAMPING_CONST));
            node.setY(node.getY() + (node.getVy() * DAMPING_CONST));
        }

        for (int i = 0; i < nodes.size(); ++i) {
            Node node = nodes.get(i);
            Double linkForceX = 0.0;
            Double linkForceY = 0.0;
            Double magForceX = 0.0;
            Double magForceY = 0.0;

            // calculate the force from the springs
            for (int other : adjList.get(i)) {
                Node link = nodes.get(other);
                linkForceX += PhysicsUtils.calculateSpringForce(
                        calculateDistance(node.getX(), link.getX()),
                        Constants.SPRING_CONST);
                linkForceY += PhysicsUtils.calculateSpringForce(
                        calculateDistance(node.getY(), link.getY()),
                        Constants.SPRING_CONST);
            }

            // calculate the force from the magnets
            for(Node otherNode: nodes) {
                if (otherNode.equals(node)) {
                    continue;
                }
                double distance = calculateDistance(node, otherNode);
                double magForce = -PhysicsUtils.calculateMagneticForce(Constants.MAGNET_CONST, distance);
                magForceX += Math.cos(PhysicsUtils.calculateTheta(node, otherNode)) * magForce;
                magForceY += Math.sin(PhysicsUtils.calculateTheta(node, otherNode)) * magForce;
            }

            Double totalForceX = linkForceX + magForceX;
            Double totalForceY = linkForceY + magForceY;

            // The further the node is from the center, the more we pull it towards the center
            totalForceX += Constants.GRAVITY_CONST * calculateDistance(node.getX(), (double)Constants.CENTER_X);
            totalForceY += Constants.GRAVITY_CONST * calculateDistance(node.getY(), (double)Constants.CENTER_Y);

            node.setVx(Math.max(-Constants.SLOW_DOWN, Math.min(Constants.SLOW_DOWN, node.getVx() + totalForceX)));
            node.setVy(Math.max(-Constants.SLOW_DOWN, Math.min(Constants.SLOW_DOWN, node.getVy() + totalForceY)));
        }

        updateObservers();
    }

    private void updateObservers() throws IOException {
        for(Observer observer: observers) {
            observer.update(nodes, adjList);
        }
    }

    void addObserver(Observer observer) {
        observers.add(observer);
    }
}
