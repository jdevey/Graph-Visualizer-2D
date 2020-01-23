package NetworkVis;

import java.awt.*;

class Constants {
    // Physics constants
    static final double FPS = 30.0;
    static final double TARGET = 1000 / FPS;
    static final Double SPRING_CONST = 0.001;
    static final Double MAGNET_CONST = 2000.0;
    static final Double GRAVITY_CONST = 0.002;
    static final Double SLOW_DOWN = 1.0;
    static final Double DAMPING_CONST = 0.5;
    static final int MIN_SLEEP_MS = 10;

    // Display constants
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static final int SCREEN_WIDTH = screenSize.width;
    static final int SCREEN_HEIGHT = screenSize.height;
    static final int CENTER_X = screenSize.width / 2;
    static final int CENTER_Y = screenSize.height / 2;
    static final int NODE_DIAMETER = 30;
    static final int NODE_BORDER_WIDTH = 2;
    static final int EDGE_WIDTH = 2;
    static final int EDGE_BORDER_WIDTH = 0;
}
