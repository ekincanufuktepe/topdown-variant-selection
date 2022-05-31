package tpvs.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tpvs.variants.Feature;
import tpvs.variants.FeatureModel;
import tpvs.variants.Variant;

public class VariantParser {

	public VariantParser() {

	}

	public FeatureModel parseFeatureModel(String featureModelFile) {
		FeatureModel fm = new FeatureModel();
		try {
			File file = new File(featureModelFile);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize();

			// find the source of the feature descriptions
			NodeList nodeList = doc.getElementsByTagName("struct");
			for(int itr = 0; itr < nodeList.getLength(); itr++) {  
				Node node = nodeList.item(itr);
				if(node.getChildNodes().getLength() != 0) {
					captureFeatureName(node, fm);
				}
			}  
		}   
		catch (Exception e) {  
			e.printStackTrace();  
		}
		return fm;
	}

	public void captureFeatureName(Node rootNode, FeatureModel fm) {
		NodeList nodeList = rootNode.getChildNodes();
		for(int itr = 0; itr < nodeList.getLength(); itr++) {  
			Node node = nodeList.item(itr);
			if(node.getChildNodes().getLength() != 0) {
				captureFeatureName(node, fm);
			}
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;  
				if(eElement.getAttributes().getNamedItem("name") != null ) {
					Feature feature = new Feature(eElement.getAttributes().getNamedItem("name").getNodeValue());
					fm.getFeatureList().add(feature);
					//					System.out.println("feature name: "+ eElement.getAttributes().getNamedItem("name").getNodeValue());
				}
			} 	 
		}
	}

	public List<Variant> parseVariants(String variantDirectory) {
		List<Variant> variants = new ArrayList<Variant>();
		File directoryPath = new File(variantDirectory);
		// List of all variant files
		File filesList[] = directoryPath.listFiles();
		int idCounter = 1;
		for(File file : filesList) {
			Variant variant = new Variant();
			variant.setVariantID(idCounter);
			idCounter++;
			try {
				BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
				String line = "";
				while((line = br.readLine()) != null) {
					Feature feature = null;
					if(line.startsWith("\"") && line.endsWith("\"")) {
						feature = new Feature(line.substring(1, line.length()-1));
					}
					else if(line.equals("")) {
						continue;
					}
					else {
						feature = new Feature(line);
					}
					variant.getFeatureList().add(feature);
				}
				variants.add(variant);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return variants;
	}
}