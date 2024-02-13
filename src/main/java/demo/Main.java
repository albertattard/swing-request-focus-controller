package demo;

import javax.swing.*;

public class Main {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JFrame application = new JFrame("Swing - a replacement for sun.awt.RequestFocusController");
            application.setContentPane(new DemoPanel());
            application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            application.pack();
            application.setLocationByPlatform(true);
            application.setVisible(true);
        });
    }
}
