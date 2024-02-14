package demo;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import static demo.Utils.getClassSimpleName;

public class DemoPanel extends JPanel {

    public DemoPanel() {
        super(new GridBagLayout());
        initComponents();
    }

    private void initComponents() {
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Non Empty Text Field"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(createNonEmptyTextField(), constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Numbers Only Text Field"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(createNumbersOnlyTextField(), constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Radio Button"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(createRadioButton(), constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Check Box"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(createCheckbox(), constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Button"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        add(createButton(), constraints);
    }

    private JTextField createNonEmptyTextField() {
        final JTextField nonEmptyTextField = new JTextField(20);
        nonEmptyTextField.setToolTipText("This text field should not be empty");
        nonEmptyTextField.addFocusListener(createFocusAdapter());
        nonEmptyTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent component) {
                final String text = ((JTextComponent) component).getText();
                final boolean valid = !text.isEmpty();
                if (!valid) {
                    System.out.println("Input Verifier - JTextField cannot have empty text");
                }
                return valid;
            }
        });
        return nonEmptyTextField;
    }

    private JTextField createNumbersOnlyTextField() {
        final JTextField numbersOnlyTextField = new JTextField(20);
        numbersOnlyTextField.setToolTipText("This text field should only contain numbers");
        numbersOnlyTextField.addFocusListener(createFocusAdapter());
        numbersOnlyTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent component) {
                final String text = ((JTextComponent) component).getText();
                final boolean valid = text.matches("\\d+");
                if (!valid) {
                    System.out.println("Input Verifier - JTextField should only contain numbers");
                }
                return valid;
            }
        });
        return numbersOnlyTextField;
    }

    private JRadioButton createRadioButton() {
        final JRadioButton radioButton = new JRadioButton("Java");
        radioButton.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent component) {
                final boolean selected = ((JRadioButton) component).isSelected();
                if (!selected) {
                    System.out.println("Input Verifier - JRadioButton should be selected");
                }
                return selected;
            }
        });
        radioButton.addFocusListener(createFocusAdapter());
        return radioButton;
    }

    private JCheckBox createCheckbox() {
        final JCheckBox checkBox = new JCheckBox("Java");
        checkBox.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent component) {
                final boolean selected = ((JCheckBox) component).isSelected();
                if (!selected) {
                    System.out.println("Input Verifier - JCheckBox should be selected");
                }
                return selected;
            }
        });
        checkBox.addFocusListener(createFocusAdapter());
        return checkBox;
    }

    private JButton createButton() {
        final JButton button = new JButton("Submit");
        button.addFocusListener(createFocusAdapter());
        button.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent component) {
                /* We can run validation and decide whether we should yield focus */
                return true;
            }
        });
        return button;
    }

    private FocusAdapter createFocusAdapter() {
        return new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent event) {
                final String name = getClassSimpleName(event.getComponent());
                System.out.printf("Focus Adapter - %s Focus gained%n", name);
            }

            @Override
            public void focusLost(final FocusEvent event) {
                final String name = getClassSimpleName(event.getComponent());
                System.out.printf("Focus Adapter - %s Focus lost%n", name);
            }
        };
    }
}
