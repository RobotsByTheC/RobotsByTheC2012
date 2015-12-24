/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package amawrapper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Ben
 */
public class VideoPropertiesDialog extends JFrame {

    private JTextField imageURL, teamNumber, username, zoomLevel;
    private JPasswordField password;
    private JButton ok, cancel;
    private JCheckBox override, loginEnabled, showControls;
    private JComboBox zoomChooser;
    public final String[] zoomVals = {"Auto", "Free", "Custom"};

    public VideoPropertiesDialog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Camera Properties");
        loginEnabled = new JCheckBox("Camera requires login", Prefs.loginEnabled);
        loginEnabled.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                password.setEnabled(loginEnabled.isSelected());
                username.setEnabled(loginEnabled.isSelected());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        username = new JTextField(Prefs.username);
        username.setEnabled(Prefs.loginEnabled);
        password = new JPasswordField(Prefs.password);
        password.setEnabled(Prefs.loginEnabled);
        //try {
        teamNumber = new JFormattedTextField(/*
                 * new MaskFormatter("####")
                 */);
        //} catch (ParseException ex) {
        //}
        teamNumber.setText(Prefs.teamNumber);
        teamNumber.setEnabled(!Prefs.override);
        imageURL = new JTextField(Prefs.customURL);
        ok = new JButton("Ok");
        ok.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                String originalTeamNumber = teamNumber.getText();
                String addingString = "";
                for (int i = 0; i < 4 - originalTeamNumber.length(); i++) {
                    addingString += "0";
                }
                String newTeam = addingString + originalTeamNumber;
                Prefs.customURL = (!override.isSelected()) ? "http://10." + newTeam.substring(0, 2) + "." + newTeam.substring(2) + ".11/mjpg/video.mjpg" : imageURL.getText();
                Prefs.teamNumber = teamNumber.getText();
                Prefs.loginEnabled = loginEnabled.isSelected();
                Prefs.password = password.getText();
                Prefs.username = username.getText();
                Prefs.showControls = showControls.isSelected();
                Prefs.customZoomValue = zoomLevel.getText();
                Prefs.zoomType = ((Integer) zoomChooser.getSelectedIndex()).toString();
                dispose();
                Prefs.writeToPrefsDocument(Prefs.getPrefsDoc(Prefs.getSaveFileLocation()));
                Prefs.savePrefsFile(Prefs.getPrefsDoc(Prefs.getSaveFileLocation()), Prefs.getSaveFileLocation());
                AmaWrapper.startMediaApplet();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        cancel = new JButton("Cancel");
        cancel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dispose();
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        override = new JCheckBox("Use custom URL", Prefs.override);
        imageURL.setEnabled(override.isSelected());
        override.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                imageURL.setEnabled(override.isSelected());
                teamNumber.setEnabled(!override.isSelected());
                Prefs.override = override.isSelected();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        showControls = new JCheckBox("Show Controls");
        showControls.setSelected(Prefs.showControls);
        zoomLevel = new JTextField(Prefs.customZoomValue);
        zoomChooser = new JComboBox(zoomVals);
        //zoomChooser.setSelectedIndex(Integer.parseInt(Prefs.zoomType));
        zoomChooser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                zoomLevel.setVisible(zoomChooser.getSelectedItem() == zoomVals[2]);
            }
        });
        zoomLevel.setVisible(zoomChooser.getSelectedItem() == zoomVals[2]);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setLayout(new GridLayout(0, 2));
        add(new JLabel("Team Number:"));
        add(teamNumber);
        add(override);
        add(new JPanel());
        add(new JLabel("Custom image url:"));
        add(imageURL);
        add(loginEnabled);
        add(new JPanel());
        add(username);
        add(password);
        add(showControls);
        add(new JPanel());
        add(zoomChooser);
        add(zoomLevel);
        add(ok);
        add(cancel);
        pack();
        setVisible(true);
    }
}
