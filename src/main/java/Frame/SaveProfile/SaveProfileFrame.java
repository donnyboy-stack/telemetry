package Frame.SaveProfile;

import App.Profile.ProfileInterface;
import Frame.EditProfile.AbstractEditProfileFrame;
import Menu.Main.Observer.MainMenuObserverInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SaveProfileFrame extends AbstractSaveProfileFrame implements WindowListener {

    final protected String BUTTON_YES = "Yes";
    final protected String BUTTON_NO  = "No";
    final protected String CHANGE_TEXT = "You have made changes to your profile.";
    final protected String PROMPT_TEXT = "Would you like to save?";


    public SaveProfileFrame(ProfileInterface profile) {
        super(profile);

        setTitle(FRAME_TITLE);

        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Set the frame's layout
         */
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addSaveProfileFrame();
    }

    public SaveProfileFrame(ProfileInterface profile, MainMenuObserverInterface observer){
        this(profile);
        observers.add(observer);
    }

    protected void addSaveProfileFrame(){
//        JPanel container = new JPanel();
        JPanel container = new JPanel(new BorderLayout());
        getContentPane().add(container);

        JPanel textPanel = new JPanel();
        container.add(textPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        container.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

        JPanel promptPanel = new JPanel();
        container.add(promptPanel, BorderLayout.CENTER);

        /*
         * Position and size the panel
         */
//        Dimension dim = new Dimension(FRAME_WIDTH - AXIS_PADDING, FRAME_HEIGHT - AXIS_PADDING);
//        container.setMinimumSize(dim);
//        panel.setSize(dim);


        textPanel.add(new JLabel(CHANGE_TEXT));

        promptPanel.add(new JLabel(PROMPT_TEXT));

        JButton yesButton = new JButton(BUTTON_YES);
        buttonPanel.add(yesButton);

        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("SAVING!!!");
                for (MainMenuObserverInterface observer : observers) {
                    observer.doSaveProfile();
                    quit(0);
                }
            }
        });

        JButton noButton = new JButton(BUTTON_NO);
        buttonPanel.add(noButton);

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit(0);
            }
        });

        buttonPanel.getRootPane().setDefaultButton(yesButton);
    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
        if (profile.hasChanged()) {
            showFrame();
        } else {
            quit(0);
        }
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }

    public void addObserver(MainMenuObserverInterface observer){
        observers.add(observer);
    }

    public void setProfile(ProfileInterface prof){
        this.profile = prof;
    }

    private void quit(int status){
        dispose();
        Runtime.getRuntime().exit(status);
    }
}
