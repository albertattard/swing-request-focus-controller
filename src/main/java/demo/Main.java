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

public class Main {
    public static void main(final String[] args) {
        UIManager.getDefaults();
        setOurController();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addVetoableChangeListener("permanentFocusOwner",
                new FocusChangeListener());

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
            Method setter = AccessController.doPrivileged((PrivilegedExceptionAction<Method>) () -> {
                Class<?> acClass = Class.forName("sun.awt.RequestFocusController");
                Method m = Component.class.getDeclaredMethod("setRequestFocusController", acClass);
                m.setAccessible(true);
                return m;
            });
            setter.invoke(null, new OurRequestFocusController());
        } catch (IllegalAccessException | InvocationTargetException | PrivilegedActionException e) {
            throw new RuntimeException(e);
        }
    }
}
