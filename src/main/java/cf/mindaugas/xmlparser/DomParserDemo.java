package cf.mindaugas.xmlparser;

import cf.mindaugas.xmlparser.model.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DomParserDemo {

	public static void main(String[] args) {

		try {
			String xml = "<?xml version = \"1.0\"?>\n" +
					"<class>\n" +
					"   <student rollno = \"393\">\n" +
					"      <firstname>dinkar</firstname>\n" +
					"      <lastname>kad</lastname>\n" +
					"      <nickname>dinkar</nickname>\n" +
					"      <marks>85</marks>\n" +
					"   </student>\n" +
					"   \n" +
					"   <student rollno = \"493\">\n" +
					"      <firstname>Vaneet</firstname>\n" +
					"      <lastname>Gupta</lastname>\n" +
					"      <nickname>vinni</nickname>\n" +
					"      <marks>95</marks>\n" +
					"   </student>\n" +
					"   \n" +
					"   <student rollno = \"593\">\n" +
					"      <firstname>jasvir</firstname>\n" +
					"      <lastname>singn</lastname>\n" +
					"      <nickname>jazz</nickname>\n" +
					"      <marks>90</marks>\n" +
					"   </student>\n" +
					"</class>";

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource(new StringReader(xml));
			Document doc = dBuilder.parse(inputSource);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList studentXmlElems = doc.getElementsByTagName("student");
			System.out.println("----------------------------");

			List<Student> students = new ArrayList<Student>();

			for (int temp = 0; temp < studentXmlElems.getLength(); temp++) {

				Node nNode = studentXmlElems.item(temp);
				// System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					students.add(new Student(
							eElement.getElementsByTagName("firstname").item(0).getTextContent(),
							eElement.getElementsByTagName("lastname").item(0).getTextContent()));
				}

				// if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				// 	Element eElement = (Element) nNode;
				// 	System.out.println("Student roll no : " + eElement.getAttribute("rollno"));
				// 	System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
				// 	System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
				// 	System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
				// 	System.out.println("Marks : " + eElement.getElementsByTagName("marks").item(0).getTextContent());
				// }
			}

			students.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}