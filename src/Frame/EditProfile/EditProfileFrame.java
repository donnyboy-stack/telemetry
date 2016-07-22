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
import javax.swing.SpringLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

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

        setTitle(FRAME_TITLE);

        /*
         * The app should not quit when this view is closed
         */
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

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

        /*
         * Add the panel to edit the data types
         */
        addEditDataTypesPanel();
    }

    protected void addEditDataTypesPanel () {
        JPanel panel = new JPanel();

        /*
         * Layout constraints
         */
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;

        /*
         * Set the panel layout
         */
        setLayout(new GridBagLayout());

        /*
         * Add the border and title to the panel
         */
        panel.setBorder(BorderFactory.createTitledBorder(TITLE_EDIT_TYPES));

        /*
         * Position and size the panel
         */
        panel.setPreferredSize(new Dimension(FRAME_WIDTH - AXIS_PADDING, FRAME_HEIGHT - AXIS_PADDING));

        /*
         * Add type select
         */
        JLabel typeLabel = new JLabel(LABEL_TYPE);
        panel.add(typeLabel, c);

        c.gridx++;

        JComboBox typeSelect = buildDataTypeSelect();
        panel.add(typeSelect, c);

        c.gridx = 0;
        c.gridy++;

        /*
         * Add name field
         */
        JLabel nameLabel = new JLabel(LABEL_TYPE_NAME);
        panel.add(nameLabel, c);

        c.gridx++;

        JTextField nameField = new JTextField();
        panel.add(nameField, c);

        c.gridx = 0;
        c.gridy++;

        /*
         * Add units field
         */
        JLabel unitsLabel = new JLabel(LABEL_TYPE_UNITS);
        panel.add(unitsLabel, c);

        c.gridx++;

        JTextField unitsField = new JTextField();
        panel.add(unitsField, c);

        c.gridx = 0;
        c.gridy++;

        /*
         * Add color field
         */
        JLabel colorLabel = new JLabel(LABEL_TYPE_COLOR);
        panel.add(colorLabel, c);

        c.gridx++;

        JTextField colorField = new JTextField();
        panel.add(colorField, c);

        c.gridx = 0;
        c.gridy++;

        /*
         * Listen for type selector changes
         */
        typeSelect.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String choice          = (String) typeSelect.getSelectedItem();
                DataTypeInterface type = profile.getDataSource().getTypes().get(choice);

                String name  = "";
                String units = "";
                String color = "";

                if (type != null) {
                    name  = type.getName();
                    units = type.getUnits();
                    color = String.format("#%05X",  0xFFFFFF & type.getColor().getRGB());
                }

                nameField.setText(name);
                unitsField.setText(units);
                colorField.setText(color);
            }
        });

        /*
         * Add the panel to the frame
         */
        add(panel);
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
