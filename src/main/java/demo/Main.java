package demo;

import focus.FocusChangeListener;
import focus.OurRequestFocusController;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;

public class Main {

    public static void main(final String[] args) {
        final boolean useOldFocusController = isUseOldFocusController(args);

        UIManager.getDefaults();

        if (useOldFocusController) {
            System.out.println("Using the old focus controller");
            setOurController();
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
                    .addVetoableChangeListener("permanentFocusOwner", new FocusChangeListener());
        }

        SwingUtilities.invokeLater(() -> {
            final JFrame application = new JFrame("Swing - a replacement for sun.awt.RequestFocusController");
            application.setContentPane(new DemoPanel());
            application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            application.pack();
            application.setLocationByPlatform(true);
            application.setVisible(true);
        });
    }

    private static void setOurController() {
        try {
            final Method setter = AccessController.doPrivileged((PrivilegedExceptionAction<Method>) () -> {
                final Class<?> type = Class.forName("sun.awt.RequestFocusController");
                final Method method = Component.class.getDeclaredMethod("setRequestFocusController", type);
                method.setAccessible(true);
                return method;
            });
            setter.invoke(null, new OurRequestFocusController());
        } catch (final IllegalAccessException | InvocationTargetException | PrivilegedActionException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isUseOldFocusController(final String... args) {
        return Arrays.stream(args)
                .anyMatch("--useOldFocusController"::equalsIgnoreCase);
    }
}
