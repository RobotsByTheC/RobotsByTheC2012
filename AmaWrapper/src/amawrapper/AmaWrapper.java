/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package amawrapper;

import ama.MediaApplet;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Ben
 */
public class AmaWrapper {

    /**
     * @param args the command line arguments
     */
    public static MediaApplet applet;
    private static VideoPropertiesDialog prefsDialog;
    public static PrefsLoader prefsLoader;

    public static void main(String[] args) throws URISyntaxException, IOException {
//        try {
//            // TODO code application logic here
//            prefsLoader = new PrefsLoader();
//        } catch (ParserConfigurationException ex) {
//        }

//        System.out.println("Loaded preferences from: " + prefsLoader.getSaveFileLocation());
        
        Prefs.loadPrefsFile();

        Prefs.loadPrefsToApp(Prefs.getPrefsDoc(Prefs.getSaveFileLocation()));
        prefsDialog = new VideoPropertiesDialog();

    }

    public static void startMediaApplet() {
        String[] params;
        params = new String[3];
        if (Prefs.loginEnabled && Prefs.showControls) {
            params = new String[7];
            params[0] = "--user";
            params[1] = Prefs.username;
            params[2] = "--password";
            params[3] = Prefs.password;
        }
        if (!Prefs.loginEnabled && !Prefs.showControls) {
            params = new String[4];
            params[0] = "--hide-video-controls";
        }
        if (Prefs.loginEnabled&&!Prefs.showControls){
            params = new String[8];
            params[0] = "--user";
            params[1] = Prefs.username;
            params[2] = "--password";
            params[3] = Prefs.password;
            params[4] = "--hide-video-controls";
        }
        
        params[params.length-3] = "--zoom";
        if(Prefs.zoomType.equals("2")){
            params[params.length-2] = Prefs.customZoomValue;
        }
        else {
            params[params.length-2] = prefsDialog.zoomVals[Integer.parseInt(Prefs.zoomType)].toLowerCase();
        }
        params[params.length-1] = Prefs.customURL;
        prefsDialog = null;
        MediaApplet.main(params);
    }
}
