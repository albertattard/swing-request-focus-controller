package focus;

import sun.awt.CausedFocusEvent;
import sun.awt.RequestFocusController;

import java.awt.*;

public class OurRequestFocusController implements RequestFocusController {
    @Override
    public boolean acceptRequestFocus(Component from, Component to, boolean temporary, boolean focusedWindowChangeAllowed, CausedFocusEvent.Cause cause) {
        System.out.println("Focus Request (from: " + from + "; to: " + to + "; temporary=" + temporary);
        return true;
    }

    @Override
    public Component getGroupSelection(Component component, CausedFocusEvent.Cause cause) {
        return component;
    }
}
