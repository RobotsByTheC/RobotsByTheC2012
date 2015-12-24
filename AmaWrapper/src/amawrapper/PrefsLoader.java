/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package amawrapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author
 * team 2084
 */
/**
 * Ben, I changed this class just so it can read/write to "save.xml" when it's in the same folder in the JAR
 * because I found out that writing to files inside of a JAR (besides from when you run it in NetBeans) doesn't work.
 * When you try to double-click on the JAR normally in Explorer/Finder it won't load the prefs
 * (I tried InputStream too, which works with reading but not writing) so I guess just to make it nice and neat, I changed this.
 * 
 * The only problem is that every time you build/run the project in NetBeans,
 * it deletes "save.xml" (should go in the dist folder next to the JAR)!!! So that's kind of annoying, but the old code is still down there too.
 * We can change it back, but then you have to open NetBeans to run AmaWrapper. I don't know which one is easier...
 * --------------- actually I just realized that I was pressing 'Clean and Build' so it was deleting save.xml because of that... oops
 *
 * Peter, I finished the part you were working on and I think some of this code should be moved into Prefs.java
 * 
 * 
 * ---------------------------------------------------------------------------------
 * THE FOLLOWING CODE IS OBSOLETE - I have moved it to Prefs.java and cleaned it up
 * ---------------------------------------------------------------------------------
 */
public class PrefsLoader
{
    private URI saveFileLocation = null;
    
    public PrefsLoader() throws URISyntaxException, IOException, ParserConfigurationException
    {
        /// get the path to the jar and to the file (if it exists)
        File pathToJAR = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        String saveFileURI = "file:" + pathToJAR.getParent() + File.separatorChar + "save.xml"; /// getParent() returns the path to the folder that the JAR is in
        //This replaces backslashes with forwardslashes and gets rid of 'C:' in the URI on Windows
        //It didn't work on my computer before I did this
        saveFileURI = saveFileURI.replace('\\', '/');
        saveFileURI = saveFileURI.replace("C:", "");
        System.out.println(saveFileURI);
        /// check if the file exists, create it if it isn't
        File saveFile = new File(new URI(saveFileURI));
        if(!saveFile.exists())
        {
            System.out.println("save file not found at: " + saveFileURI);
            System.out.println("creating new save file");
            
            saveFile.createNewFile();
            
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            docBuilder = docBuilderFactory.newDocumentBuilder();
            Document d = docBuilder.newDocument();
            d.normalize();
            /*Node teamNumberNode = d.getElementsByTagName("teamnumber").item(0);
            Node useCustomURLNode = d.getElementsByTagName("usecustomurl").item(0);
            Node customURLNode = d.getElementsByTagName("customurl").item(0);
            Node loginRequiredNode = d.getElementsByTagName("loginrequired").item(0);
            Node usernameNode = d.getElementsByTagName("username").item(0);
            Node passwordNode = d.getElementsByTagName("password").item(0);
            Node showControlsNode = d.getElementsByTagName("showcontrols").item(0);
            Node zoomTypeNode = d.getElementsByTagName("zoomtype").item(0);
            */
            d.appendChild(d.createElement("prefs"));
            Node prefs = d.getElementsByTagName("prefs").item(0);
            prefs.appendChild(d.createElement("teamnumber"));
            prefs.appendChild(d.createElement("usecustomurl"));
            prefs.appendChild(d.createElement("customurl"));
            prefs.appendChild(d.createElement("loginrequired"));
            prefs.appendChild(d.createElement("username"));
            prefs.appendChild(d.createElement("password"));
            prefs.appendChild(d.createElement("showcontrols"));
            prefs.appendChild(d.createElement("zoomtype"));
            prefs.appendChild(d.createElement("customzoomvalue"));
            
            d.getElementsByTagName("teamnumber").item(0).setTextContent(Prefs.teamNumber);
            d.getElementsByTagName("usecustomurl").item(0).setTextContent(Prefs.override?"true":"false");
            d.getElementsByTagName("customurl").item(0).setTextContent(Prefs.customURL);
            d.getElementsByTagName("loginrequired").item(0).setTextContent(Prefs.loginEnabled?"true":"false");
            d.getElementsByTagName("username").item(0).setTextContent(Prefs.username);
            d.getElementsByTagName("password").item(0).setTextContent(Prefs.password);
            d.getElementsByTagName("showcontrols").item(0).setTextContent(Prefs.showControls?"true":"false");
            d.getElementsByTagName("zoomtype").item(0).setTextContent(Prefs.zoomType);
            d.getElementsByTagName("customzoomvalue").item(0).setTextContent(Prefs.customZoomValue);
            Prefs.savePrefsFile(d, new URI(saveFileURI));
        }
        
        saveFileLocation = new URI(saveFileURI);
        //saveFileLocation = this.getClass().getClassLoader().getResource("amawrapper/save.xml").toURI();
    }
    
    public URI getSaveFileLocation()
    {
        return saveFileLocation;
    }
}