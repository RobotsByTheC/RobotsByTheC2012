/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cameraloader;

import ama.MediaApplet;

/**
 *
 * @author Ben
 */
public class CameraLoader {

    /**
     * @param args the command line arguments
     */
    public static MediaApplet applet;
    private static Prefs cam1Prefs, cam2Prefs;
    //private static VideoPropertiesDialog prefsDialog;
    //public static PrefsLoader prefsLoader;

    public static void main(String[] args) {
//        try {
//            // TODO code application logic here
//            prefsLoader = new PrefsLoader();
//        } catch (ParserConfigurationException ex) {
//        }

//        System.out.println("Loaded preferences from: " + prefsLoader.getSaveFileLocation());
        cam1Prefs = new Prefs();
        cam1Prefs.loadPrefsFile("save.xml");
        cam1Prefs.loadPrefsToApp(cam1Prefs.getPrefsDoc(cam1Prefs.getSaveFileLocation()));
        //prefsDialog = new VideoPropertiesDialog();
        startMediaApplet(cam1Prefs);
        cam2Prefs = new Prefs();

        cam2Prefs.loadPrefsFile("save2.xml");
        cam2Prefs.loadPrefsToApp(cam2Prefs.getPrefsDoc(cam2Prefs.getSaveFileLocation()));
        //prefsDialog = new VideoPropertiesDialog();
        startMediaApplet(cam2Prefs);
    }

    public static void startMediaApplet(Prefs cam1Prefs) {
        String[] params;
        params = new String[1];
        if (cam1Prefs.loginEnabled && cam1Prefs.showControls) {
            params = new String[5];
            params[0] = "--user";
            params[1] = cam1Prefs.username;
            params[2] = "--password";
            params[3] = cam1Prefs.password;
        }
        if (!cam1Prefs.loginEnabled && !cam1Prefs.showControls) {
            params = new String[4];
            params[0] = "--hide-video-controls";
        }
        if (cam1Prefs.loginEnabled && !cam1Prefs.showControls) {
            params = new String[6];
            params[0] = "--user";
            params[1] = cam1Prefs.username;
            params[2] = "--password";
            params[3] = cam1Prefs.password;
            params[4] = "--hide-video-controls";
        }

//        params[params.length-3] = "--zoom";
//        if(  cam1Prefs.zoomType.equals("2")){
//            params[params.length-2] =   cam1Prefs.customZoomValue;
//        }
//        else {
//            params[params.length-2] =   cam1Prefs.zoomVals[Integer.parseInt(  cam1Prefs.zoomType)].toLowerCase();
//        }
        params[params.length - 1] = cam1Prefs.customURL;
        //prefsDialog = null;
        MediaApplet.main(params);
    }
}
