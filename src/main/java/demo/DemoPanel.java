package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.Function;

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
        final JTextField nonEmptyTextField = createNonEmptyTextField();
        nonEmptyTextField.addFocusListener(createFocusAdapter());
        add(nonEmptyTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Numbers Only Text Field"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        final JTextField numbersOnlyTextField = createNumbersOnlyTextField();
        numbersOnlyTextField.addFocusListener(createFocusAdapter());
        add(numbersOnlyTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Radio Button"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        final JRadioButton radioButton = new JRadioButton("Java");
        radioButton.addFocusListener(createFocusAdapter());
        add(radioButton, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Check Box"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        final JCheckBox checkBox = new JCheckBox("Java");
        checkBox.addFocusListener(createFocusAdapter());
        add(checkBox, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Button"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        final JButton button = new JButton("Submit");
        button.addFocusListener(createFocusAdapter());
        add(button, constraints);
    }

    private JTextField createNonEmptyTextField() {
        final JTextField nonEmptyTextField = new JTextField(20);
        nonEmptyTextField.setToolTipText("This text field should not be empty");
        nonEmptyTextField.setInputVerifier(createInputVerifier(text -> !text.isEmpty(), "JTextField cannot have empty text"));
        return nonEmptyTextField;
    }

    private JTextField createNumbersOnlyTextField() {
        final JTextField numbersOnlyTextField = new JTextField(20);
        numbersOnlyTextField.setToolTipText("This text field should only contain numbers");
        numbersOnlyTextField.setInputVerifier(createInputVerifier(text -> text.matches("\\d+"), "JTextField should only contain numbers"));
        return numbersOnlyTextField;
    }

    private InputVerifier createInputVerifier(final Function<String, Boolean> validator, final String message) {
        return new InputVerifier() {
            @Override
            public boolean verify(final JComponent component) {
                final String text = ((JTextField) component).getText();
                final boolean valid = validator.apply(text);
                if (!valid) {
                    System.out.printf("InputVerifier - %s%n", message);
                }
                return valid;
            }
        };
    }

    private FocusAdapter createFocusAdapter() {
        return new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent event) {
                final String name = event.getComponent().getClass().getSimpleName();
                System.out.printf("FocusAdapter - %s Focus gained%n", name);
            }

            @Override
            public void focusLost(final FocusEvent event) {
                final String name = event.getComponent().getClass().getSimpleName();
                System.out.printf("FocusAdapter - %s Focus lost%n", name);
            }
        };
    }
}
