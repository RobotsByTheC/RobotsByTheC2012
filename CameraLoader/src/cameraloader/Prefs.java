/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cameraloader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Ben
 */
public class Prefs {

    private Document prefsDoc;
    public boolean override = false;
    public String teamNumber = "2084";
    public String customURL = "http://10.20.84.11/mjpg/video.mjpg";
    public boolean loginEnabled = false;
    public String username = "root";
    public String password = "root";
    public boolean showControls = true;
    public String zoomType = "0";
    public String customZoomValue = "1";
    private URI saveFileLocation = null;

    public Document getPrefsDoc(URI location) {
        if (prefsDoc == null) {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            try {
                docBuilder = docBuilderFactory.newDocumentBuilder();
                prefsDoc = docBuilder.parse(new File(location));
            } catch (ParserConfigurationException ex) {
            } catch (IOException e1) {
                System.err.println("Couldn't read preferences");
            } catch (SAXException e2) {
                System.err.println("Couldn't read preferences");
            }

        }
        return prefsDoc;
    }

    public void loadPrefsFile(String filename) {
        /// get the path to the jar and to the file (if it exists)
        File pathToJAR = new File(Prefs.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String saveFileURI = "file:" + pathToJAR.getParent() + File.separatorChar + filename; /// getParent() returns the path to the folder that the JAR is in
        //This replaces backslashes with forwardslashes and gets rid of 'C:' in the URI on Windows
        //It didn't work on my computer before I did this
        saveFileURI = saveFileURI.replace('\\', '/');
        saveFileURI = saveFileURI.replace("C:", "");
        /// check if the file exists, create it if it isn't
        File saveFile = null;
        try {
            saveFile = new File(new URI(saveFileURI));
        } catch (URISyntaxException ex) {
            System.err.println("Error");
            System.exit(1);
        }
        if (!saveFile.exists()) {
            System.out.println("Save file not found at: " + saveFileURI);
            System.out.println("Creating new save file");
            try {
                saveFile.createNewFile();
            } catch (IOException ex) {
                System.err.println("Failed to create save file");
            }

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            try {
                docBuilder = docBuilderFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                System.err.println("Failed to init document builder");
            }
            Document d = docBuilder.newDocument();
            d.normalize();
            /*
             * Node teamNumberNode =
             * d.getElementsByTagName("teamnumber").item(0); Node
             * useCustomURLNode =
             * d.getElementsByTagName("usecustomurl").item(0); Node
             * customURLNode = d.getElementsByTagName("customurl").item(0); Node
             * loginRequiredNode =
             * d.getElementsByTagName("loginrequired").item(0); Node
             * usernameNode = d.getElementsByTagName("username").item(0); Node
             * passwordNode = d.getElementsByTagName("password").item(0); Node
             * showControlsNode =
             * d.getElementsByTagName("showcontrols").item(0); Node zoomTypeNode
             * = d.getElementsByTagName("zoomtype").item(0);
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

//            d.getElementsByTagName("teamnumber").item(0).setTextContent(Prefs.teamNumber);
//            d.getElementsByTagName("usecustomurl").item(0).setTextContent(Prefs.override ? "true" : "false");
//            d.getElementsByTagName("customurl").item(0).setTextContent(Prefs.customURL);
//            d.getElementsByTagName("loginrequired").item(0).setTextContent(Prefs.loginEnabled ? "true" : "false");
//            d.getElementsByTagName("username").item(0).setTextContent(Prefs.username);
//            d.getElementsByTagName("password").item(0).setTextContent(Prefs.password);
//            d.getElementsByTagName("showcontrols").item(0).setTextContent(Prefs.showControls ? "true" : "false");
//            d.getElementsByTagName("zoomtype").item(0).setTextContent(Prefs.zoomType);
//            d.getElementsByTagName("customzoomvalue").item(0).setTextContent(Prefs.customZoomValue);
            writeToPrefsDocument(d);
            try {
                savePrefsFile(d, new URI(saveFileURI));
                System.out.println("Saved default prefs to file");
            } catch (URISyntaxException ex) {
                System.err.println("Invalid URI");
            }
        }
        try {
            saveFileLocation = new URI(saveFileURI);
            System.out.println("Loaded prefs from: " + saveFileURI);
        } catch (URISyntaxException ex) {
            System.err.println("Invalid URI");
        }
    }

    public void loadPrefsToApp(Document d) {
        d.getDocumentElement().normalize();
        Node teamNumberNode = d.getElementsByTagName("teamnumber").item(0);
        teamNumber = teamNumberNode.getTextContent();
        Node useCustomURLNode = d.getElementsByTagName("usecustomurl").item(0);
        override = useCustomURLNode.getTextContent().equals("true") ? true : false;
        Node customURLNode = d.getElementsByTagName("customurl").item(0);
        customURL = customURLNode.getTextContent();
        Node loginRequiredNode = d.getElementsByTagName("loginrequired").item(0);
        loginEnabled = loginRequiredNode.getTextContent().equals("true") ? true : false;
        Node usernameNode = d.getElementsByTagName("username").item(0);
        username = usernameNode.getTextContent();
        Node passwordNode = d.getElementsByTagName("password").item(0);
        password = passwordNode.getTextContent();
        Node showControlsNode = d.getElementsByTagName("showcontrols").item(0);
        showControls = showControlsNode.getTextContent().equals("true") ? true : false;
        Node zoomTypeNode = d.getElementsByTagName("zoomtype").item(0);
        zoomType = zoomTypeNode.getTextContent();
        customZoomValue = d.getElementsByTagName("customzoomvalue").item(0).getTextContent();
    }

    public void writeToPrefsDocument(Document d) {
        d.normalize();
        d.getElementsByTagName("teamnumber").item(0).setTextContent(teamNumber);
        d.getElementsByTagName("usecustomurl").item(0).setTextContent(override ? "true" : "false");
        d.getElementsByTagName("customurl").item(0).setTextContent(customURL);
        d.getElementsByTagName("loginrequired").item(0).setTextContent(loginEnabled ? "true" : "false");
        d.getElementsByTagName("username").item(0).setTextContent(username);
        d.getElementsByTagName("password").item(0).setTextContent(password);
        d.getElementsByTagName("showcontrols").item(0).setTextContent(showControls ? "true" : "false");
        d.getElementsByTagName("zoomtype").item(0).setTextContent(zoomType);
        d.getElementsByTagName("customzoomvalue").item(0).setTextContent(customZoomValue);
    }

    public void savePrefsFile(Document doc, URI filename) {
        //Copied from exampledepot.com
        try {
            // Prepare the DOM document for writing
            Source source = new DOMSource(doc);

            // Prepare the output file
            File file = new File(filename);
            Result result = new StreamResult(file);

            // Write the DOM document to the file
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            System.err.println(e.toString());
        } catch (TransformerException e) {
            System.err.println(e.toString());
        }
    }

    public URI getSaveFileLocation() {
        return saveFileLocation;
    }
}
