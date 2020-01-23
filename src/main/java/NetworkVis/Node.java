package NetworkVis;

import java.awt.*;

class Node {
    private static int idCounter = 0;
    private int id;
    private Double x;
    private Double y;
    private Double vx;
    private Double vy;
    private String label;
    private Color color;

    Node(Double x, Double y, String label) {
        this.id = idCounter++;
        this.x = x;
        this.y = y;
        vx = 0.0;
        vy = 0.0;
        this.label = label;
        this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }

    String serialize(boolean leading) {
        return  (leading ? "" : ",\n") +
                "    {" + "\n" +
                "      \"label\": \"" + this.getLabel() + "\",\n" +
                "      \"id\": " + this.getId() + ",\n" +
                "      \"x\": " + this.getX() + ",\n" +
                "      \"y\": " + this.getY() + ",\n" +
                "      \"vx\": " + this.getVx() + ",\n" +
                "      \"vy\": " + this.getVy() + "\n" +
                "    }";
    }

    Double getY() {
        return y;
    }

    void setY(Double y) {
        this.y = y;
    }

    Double getX() {
        return x;
    }

    void setX(Double x) {
        this.x = x;
    }

    Double getVx() {
        return vx;
    }

    void setVx(Double vx) {
        this.vx = vx;
    }

    Double getVy() {
        return vy;
    }

    void setVy(Double vy) {
        this.vy = vy;
    }

    private int getId() {
        return id;
    }

    String getLabel() {
        return label;
    }

    Color getColor() {
        return color;
    }

}
