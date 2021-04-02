package com.gsb;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DAO {
    private static final String url = "https://ben-ndui.com/assets/GsbAndroid/index.php?dep=";
    private static final String urlDep = "https://ben-ndui.com/assets/GsbAndroid/index.php";

    public static List<String> getLesNoms(String departement){
        List<String> LesNoms = new ArrayList<>();
        try{
            URL myURL = new URL(url + departement);
            Document doc;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(myURL.openStream());

            Element racine = doc.getDocumentElement();
            NodeList listeMed = racine.getElementsByTagName("Medecin");
            // récup des médecins
            for (int i = 0; i < listeMed.getLength(); i++) {
                Node medecin = listeMed.item(i);
                NodeList lesProprietes = medecin.getChildNodes();
                // recherche du nom
                for (int j = 0; j < lesProprietes.getLength(); j++) {
                    if (lesProprietes.item(j).getNodeName().equals("nom")) {
                        LesNoms.add(lesProprietes.item(j).getTextContent().trim());
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return LesNoms;
    }
    public static List<String> getLesDepartements(){
        List<String> LesNoms = new ArrayList<>();
        try{
            URL myURL = new URL(urlDep);
            Document doc;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(myURL.openStream());

            Element racine = doc.getDocumentElement();
            NodeList listedep = racine.getElementsByTagName("Departement");
            // récup les departements
            for (int i = 0; i < listedep.getLength(); i++) {
                Node departement = listedep.item(i);
                NodeList lesProprietes = departement.getChildNodes();
                // recherche du num
                for (int j = 0; j < lesProprietes.getLength(); j++) {
                    if (lesProprietes.item(j).getNodeName().equals("num")) {
                        LesNoms.add(lesProprietes.item(j).getTextContent().trim());
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return LesNoms;
    }
}
