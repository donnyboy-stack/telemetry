/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 21, 2016
 */

package Frame.EditProfile;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import App.Profile.ProfileInterface;
import Data.Type.Collection.DataTypeCollectionInterface;
import Data.Type.DataTypeInterface;
import Panel.Graph.AbstractGraphPanel;

public class EditProfileFrame extends AbstractEditProfileFrame {
    /*
     * Panel titles
     */
    final protected String TITLE_EDIT_TYPES = "Edit Data Types";
    final protected String TITLE_EDIT_GRAPH = "Edit Graph";

    /*
     * Field labels
     */
    final protected String LABEL_TYPE         = "Choose a Type";
    final protected String LABEL_TYPE_NAME    = "Display Name";
    final protected String LABEL_TYPE_UNITS   = "Display Units";
    final protected String LABEL_TYPE_COLOR   = "Line Color (Hexadecimal)";
    final protected String LABEL_TYPE_ENABLED = "Enabled?";
    final protected String LABEL_RANGE_MIN = "Y Axis Min";
    final protected String LABEL_RANGE_MAX = "Y Axis Max";
    final protected String LABEL_GRAPH_COlOR = "Background Color";

    /*
     * Panel buttons
     */
    final protected String BUTTON_APPLY = "Apply";
    final protected String BUTTON_DONE  = "Done";

    /*
     * Errors
     */
    final protected String ERROR_TITLE        = "Error!";
    final protected String ERROR_COLOR_FORMAT = "The color field is not formatted correctly.\nOnly hex digits are allowed.";

    /*
     * The type being edited
     */
    protected DataTypeInterface dataType;

    // The graph panel associated with the edit frame, to be able to change min and max y ranges.
    protected AbstractGraphPanel graphPanel;

    protected JTabbedPane tabs;

    public EditProfileFrame (ProfileInterface profile, AbstractGraphPanel graph) {
        super(profile);

        graphPanel = graph;
        /*
         * Set frame title
         */
        setTitle(FRAME_TITLE);

        /*
         * The minimum size of the window
         */
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        /*
         * This is a fixed size window
         */
        setResizable(false);

        /*
         * Set the frame's layout
         */
         setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

         tabs = new JTabbedPane();
         getContentPane().add(tabs);

        /*
         * Add the panel to edit the data types
         */
        addEditDataTypesPanel();
        addEditGraphPanel();
    }

    protected void addEditDataTypesPanel () {
        JPanel container = new JPanel();
        tabs.addTab("Data Types", container);

        JPanel panel = new JPanel(new GridBagLayout());
        container.add(panel);

        /*
         * Layout constraints
         */
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        /*
         * Add the border and title to the panel
         */
        panel.setBorder(BorderFactory.createTitledBorder(TITLE_EDIT_TYPES));

        /*
         * Position and size the panel
         */
        Dimension dim = new Dimension(FRAME_WIDTH - AXIS_PADDING, FRAME_HEIGHT - AXIS_PADDING);
        container.setMinimumSize(dim);
        panel.setSize(dim);

        /*
         * Add field labels
         */
        c.anchor = GridBagConstraints.LINE_START;

        panel.add(new JLabel(LABEL_TYPE), c);
        c.gridy++;

        panel.add(new JLabel(LABEL_TYPE_NAME), c);
        c.gridy++;

        panel.add(new JLabel(LABEL_TYPE_UNITS), c);
        c.gridy++;

        panel.add(new JLabel(LABEL_TYPE_COLOR), c);
        c.gridy++;

        panel.add(new JLabel(LABEL_TYPE_ENABLED), c);
        c.gridy++;

        /*
         * Add field inputs
         */
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx  = 1;
        c.gridy  = 0;

        JComboBox typeSelect = buildDataTypeSelect();
        panel.add(typeSelect, c);
        c.gridy++;

        JTextField nameField = new JTextField(15);
        panel.add(nameField, c);
        c.gridy++;

        JTextField unitsField = new JTextField(15);
        panel.add(unitsField, c);
        c.gridy++;

        JTextField colorField = new JTextField(7);
        panel.add(colorField, c);
        c.gridy++;

        JCheckBox enabledField = new JCheckBox();
        panel.add(enabledField, c);
        c.gridy++;

        /*
         * Add apply button
         */
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx  = 0;

        JButton applyButton = new JButton(BUTTON_APPLY);
        panel.add(applyButton, c);

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String choice          = (String) typeSelect.getSelectedItem();
                DataTypeInterface type = profile.getDataSource().getTypes().get(choice);

                if (type != null) {
                    type.setDisplayName(nameField.getText());
                    type.setDisplayUnits(unitsField.getText());

                    try {
                        type.setColor(new Color(Integer.parseInt(colorField.getText(), 16)));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(EditProfileFrame.this,
                            ERROR_COLOR_FORMAT,
                            ERROR_TITLE,
                            JOptionPane.WARNING_MESSAGE);
                    }

                    type.setEnabled(enabledField.isSelected());
                    profile.updateDataType(type);
                }
            }
        });

        /*
         * Add done button
         */
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx  = 1;

        JButton doneButton = new JButton(BUTTON_DONE);
        panel.add(doneButton, c);

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                EditProfileFrame.this.dispose();
            }
        });

        /*
         * Listen for type selector changes
         */
        typeSelect.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String choice          = (String) typeSelect.getSelectedItem();
                DataTypeInterface type = profile.getDataSource().getTypes().get(choice);

                String name     = "";
                String units    = "";
                String color    = "";
                boolean enabled = false;

                if (type != null) {
                    name    = type.getName();
                    units   = type.getUnits();
                    // Was %05X, made it confusing cause there were only 5 digits of 6 digit hex shown.
                    color   = String.format("%06X",  0xFFFFFF & type.getColor().getRGB());
                    enabled = type.isEnabled();
                }

                nameField.setText(name);
                unitsField.setText(units);
                colorField.setText(color);
                enabledField.setSelected(enabled);
            }
        });

        FieldListener listener = new FieldListener(applyButton, doneButton);

        for (Component comp : panel.getComponents()) {
            comp.addKeyListener(listener);
        }
    }

    protected void addEditGraphPanel() {
        JPanel container = new JPanel();
        tabs.addTab("Edit Graph", container);

        JPanel panel = new JPanel(new GridBagLayout());
        container.add(panel);

        /*
         * Layout constraints
         */
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        /*
         * Add the border and title to the panel
         */
        panel.setBorder(BorderFactory.createTitledBorder(TITLE_EDIT_GRAPH));

        Dimension dim = new Dimension(FRAME_WIDTH, FRAME_HEIGHT - AXIS_PADDING);
        container.setMinimumSize(dim);
        panel.setSize(dim);

        /*
         * Add field labels
         */
        c.anchor = GridBagConstraints.LINE_START;

        /*
         *  Add labels (text describing the entry fields.
         */
        panel.add(new JLabel(LABEL_RANGE_MIN), c);
        c.gridy++;

        panel.add(new JLabel(LABEL_RANGE_MAX), c);
        c.gridy++;

        panel.add(new JLabel(LABEL_GRAPH_COlOR), c);
        c.gridy++;

        // Reset constraints for the entry fields.
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx  = 1;
        c.gridy  = 0;

        // Add fields for min Y and max Y range.
        JTextField minRangeField = new JTextField(5);
        panel.add(minRangeField, c);
        c.gridy++;

        JTextField maxRangeField = new JTextField(5);
        panel.add(maxRangeField, c);
        c.gridy++;

        JTextField graphColorField = new JTextField(String.format("%06X",  0xFFFFFF & graphPanel.getBackgroundColor().getRGB()), 6);
        panel.add(graphColorField, c);
        c.gridy++;

        /*
         * Add apply button
         */
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx  = 0;

        JButton applyButton = new JButton(BUTTON_APPLY);
        panel.add(applyButton, c);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (minRangeField.getText().length() > 1 && maxRangeField.getText().length() > 1){
                    graphPanel.setYMin(Integer.parseInt(minRangeField.getText()));
                    graphPanel.setYMax(Integer.parseInt(maxRangeField.getText()));
                }
                // If they put enough characters to make a hex value.
                try {
                    graphPanel.setBackgroundColor(new Color(Integer.parseInt(graphColorField.getText(), 16)));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditProfileFrame.this,
                            ERROR_COLOR_FORMAT,
                            ERROR_TITLE,
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        c.anchor = GridBagConstraints.LINE_START;
        c.gridx  = 1;

        JButton doneButton = new JButton(BUTTON_DONE);
        panel.add(doneButton, c);

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                EditProfileFrame.this.dispose();
            }
        });

        FieldListener keyListen = new FieldListener(applyButton, doneButton);

        for (Component comp : panel.getComponents()) {
            comp.addKeyListener(keyListen);
        }
    }

    protected JComboBox buildDataTypeSelect() {
        DataTypeCollectionInterface types = profile.getDataSource().getTypes();

        String[] typeOptions = new String[types.size() + 1];
        int index            = 1;

        typeOptions[0] = "Choose a data type";

        for (String id : types.keySet())
            typeOptions[index++] = id;

        return new JComboBox<String>(typeOptions);
    }

    protected static class FieldListener implements KeyListener {

        JButton applyButton;
        JButton doneButton;

        public FieldListener(JButton applyButton, JButton doneButton){
            this.applyButton = applyButton;
            this.doneButton = doneButton;
        }

        public void keyTyped(KeyEvent e) { }
        public void keyReleased(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                applyButton.doClick();
            }
            else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                doneButton.doClick();
            }
        }
    }
}
