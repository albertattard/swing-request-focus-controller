package focus;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class FocusChangeListener implements VetoableChangeListener {

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {

        final Component newFocusOwner = (Component) evt.getNewValue();
        Component oldFocusOwner = (Component) evt.getOldValue();

        System.out.println("Focus Change (from: " + oldFocusOwner + "; to: " + newFocusOwner);
    }
}
