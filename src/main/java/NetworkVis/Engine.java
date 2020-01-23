package NetworkVis;

import java.io.IOException;

import static NetworkVis.Constants.MIN_SLEEP_MS;

class Engine {
    void run(Graph graph, EscapeAction escapeAction) throws IOException {
        long lastTime = System.currentTimeMillis();
        while (!escapeAction.getExit()) {
            graph.updateNodes();
            long currentTime = System.currentTimeMillis();
            long dt = currentTime - lastTime;
            long timeToSleep = (long)Constants.TARGET - dt;
            lastTime = currentTime;
            if (timeToSleep > MIN_SLEEP_MS) {
                try {
                    Thread.sleep(timeToSleep);
                }
                catch(InterruptedException ignored) {
                }
            }
        }
    }
}
