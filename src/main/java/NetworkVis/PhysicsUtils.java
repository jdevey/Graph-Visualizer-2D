package NetworkVis;

class PhysicsUtils {
    static Double calculateDistance(Double a, Double b) {
        return b - a;
    }

    static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    static double calculateDistance(Node a, Node b) {
        return Math.sqrt( Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    static Double calculateSpringForce(Double distance, Double springConstant) {
        return distance * springConstant;
    }

    static Double calculateMagneticForce(Double numeratorConstants, Double distance) {
        return numeratorConstants / (/*4 * Math.PI * */Math.pow(distance, 2));
    }

    static Double calculateTheta(Node a, Node b) {
        return Math.atan2(b.getY() - a.getY(), b.getX() - a.getX());
    }
}
