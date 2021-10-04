/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 21, 2016
 */

package Menu.Main;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends AbstractMainMenu {
    /*
     * Menu labels
     */
    final protected String MENU_PROFILE = "Profile";

    /*
     * Menu option labels
     */
    final protected String LABEL_SAVE_PROFILE = "Save Profile";
    final protected String LABEL_EDIT_PROFILE = "Edit Profile";

    public MainMenu () {
        addProfileMenu();
    }

    protected void addProfileMenu () {
        JMenu menu = new JMenu(MENU_PROFILE);

        /*
         * Add menu items to the menu
         */
        JMenuItem editProfile = new JMenuItem(LABEL_EDIT_PROFILE);
        menu.add(editProfile);

        JMenuItem saveProfile = new JMenuItem(LABEL_SAVE_PROFILE);
        menu.add(saveProfile);

        /*
         * Trigger the observers when the menu items are clicked
         */
        editProfile.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                MainMenu.this.notifyShouldEdit();
            }
        });
        
        saveProfile.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                MainMenu.this.notifyShouldSave();
            }
        });

        /*
         * Add the menu to the bar
         */
        add(menu);
    }
}