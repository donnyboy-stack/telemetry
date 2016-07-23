/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 21, 2016
 */

package sunseeker.telemetry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

import layout.SpringUtilities;

class EditProfileFrame extends AbstractEditProfileFrame {
    /*
     * Panel titles
     */
    final protected String TITLE_EDIT_TYPES = "Edit Data Types";

    /*
     * Field labels
     */
    final protected String LABEL_TYPE         = "Choose a Type";
    final protected String LABEL_TYPE_NAME    = "Display Name";
    final protected String LABEL_TYPE_UNITS   = "Display Units";
    final protected String LABEL_TYPE_COLOR   = "Line Color";
    final protected String LABEL_TYPE_ENABLED = "Enabled?";

    /*
     * The type being edited
     */
    protected DataTypeInterface dataType;

    public EditProfileFrame (ProfileInterface profile) {
        super(profile);

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
        // setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        /*
         * Add the panel to edit the data types
         */
        addEditDataTypesPanel();
    }

    protected void addEditDataTypesPanel () {
        JPanel container = new JPanel();
        getContentPane().add(container);

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
        // panel.setBorder(BorderFactory.createTitledBorder(TITLE_EDIT_TYPES));

        /*
         * Position and size the panel
         */
        // Dimension dim = new Dimension(FRAME_WIDTH - AXIS_PADDING, FRAME_HEIGHT - AXIS_PADDING);
        // container.setMinimumSize(dim);
        // panel.setSize(dim);

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

        JButton applyButton = new JButton("Apply");
        panel.add(applyButton, c);

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String choice          = (String) typeSelect.getSelectedItem();
                DataTypeInterface type = profile.getDataSource().getTypes().get(choice);

                if (type != null) {
                    type.setDisplayName(nameField.getText());
                    type.setDisplayUnits(unitsField.getText());
                    type.setColor(new Color(Integer.parseInt(colorField.getText(), 16)));
                    type.setEnabled(enabledField.isSelected());
                }
            }
        });

        /*
         * Add done button
         */
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx  = 1;

        JButton doneButton = new JButton("Done");
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
                    color   = String.format("%05X",  0xFFFFFF & type.getColor().getRGB());
                    enabled = type.isEnabled();
                }

                nameField.setText(name);
                unitsField.setText(units);
                colorField.setText(color);
                enabledField.setSelected(enabled);
            }
        });
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
}
