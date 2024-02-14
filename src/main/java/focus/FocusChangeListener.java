package focus;

import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;

import static demo.Utils.getClassSimpleName;

public class FocusChangeListener implements VetoableChangeListener {

    @Override
    public void vetoableChange(final PropertyChangeEvent event) {
        final String newFocusOwner = getClassSimpleName(event.getNewValue());
        final String oldFocusOwner = getClassSimpleName(event.getOldValue());

        System.out.printf("Focus Change - from: %s to: %s%n", oldFocusOwner, newFocusOwner);
    }
}
