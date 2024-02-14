package focus;

import sun.awt.CausedFocusEvent;
import sun.awt.RequestFocusController;

import java.awt.*;

import static demo.Utils.getClassSimpleName;

public class OurRequestFocusController implements RequestFocusController {

    @Override
    public boolean acceptRequestFocus(Component from, Component to, boolean temporary, boolean focusedWindowChangeAllowed, CausedFocusEvent.Cause cause) {
        final String fromName = getClassSimpleName(from);
        final String toName = getClassSimpleName(to);

        System.out.printf("Focus Request - from: %s to: %s temporary: %s%n", fromName, toName, temporary);

        /* Determine whether the focus should be transferred */
        return true;
    }

    @Override
    public Component getGroupSelection(final Component component, final CausedFocusEvent.Cause cause) {
        System.out.printf("Group Selection - %s - %s%n", getClassSimpleName(component), cause);
        return component;
    }
}
