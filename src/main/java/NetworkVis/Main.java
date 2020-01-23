package NetworkVis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    private static void printHelp() {
        System.out.println("You must specify a gml input file for the first argument, " +
                "and you may optionally specify an output json file for the second argument. Examples:\n" +
                "java Main karate.gml karate.json\n" +
                "java Main stateBorders.gml");
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            printHelp();
            return;
        }

        Parser parser = new GMLParser();
        Graph graph;

        try {
            graph = parser.parse(args[0]);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Create our canvas
        GraphCanvas graphCanvas = new GraphCanvas();
        graphCanvas.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

        // Create the main window
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setContentPane(graphCanvas);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a keyboard input for leaving the visualization (ESC)
        EscapeAction escapeAction = new EscapeAction();
        graphCanvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "pressed");
        graphCanvas.getActionMap().put("pressed", escapeAction);

        // Create and add observers
        VisualObserver display = new VisualObserver(graphCanvas);
        graph.addObserver(display);

        TextOutputObserver stateSerializer = null;
        if (args.length > 1) {
            PrintWriter writer;
            try {
                writer = new PrintWriter(args[1]);
            }
            catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            }
            stateSerializer = new TextOutputObserver(writer, (int)Constants.FPS * 3);
            graph.addObserver(stateSerializer);
        }

        // Start engine
        Engine physicsEngine = new Engine();

        try {
            physicsEngine.run(graph, escapeAction);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Finish serializing and close the window
        if (stateSerializer != null) {
            stateSerializer.close();
        }
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
