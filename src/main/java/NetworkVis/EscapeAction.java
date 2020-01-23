package NetworkVis;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EscapeAction extends AbstractAction {

    private boolean exit = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        this.exit = true;
    }

    boolean getExit() {
        return exit;
    }
}
