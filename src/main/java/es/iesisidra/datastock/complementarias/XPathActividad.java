package es.iesisidra.datastock.complementarias;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Requisito RF-FIC-07: Consultas de filtrado XPath sobre ficheros XML.
 */
public class XPathActividad {

    public static void ejecutarConsultaXPath(String rutaXml, String expresionXPath) {
        File archivo = new File(rutaXml);
        if (!archivo.exists()) {
            System.out.println("⚠ El archivo XML para la consulta XPath no existe.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expr = xpath.compile(expresionXPath);

            NodeList resultado = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            System.out.println("🔍 --- RESULTADOS CONSULTA XPATH [" + expresionXPath + "] ---");
            if (resultado.getLength() == 0) {
                System.out.println("No se encontraron coincidencias.");
            } else {
                for (int i = 0; i < resultado.getLength(); i++) {
                    Node nodo = resultado.item(i);
                    System.out.println("• " + nodo.getNodeName() + ": " + nodo.getTextContent().trim());
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error ejecutando la consulta XPath: " + e.getMessage());
        }
    }
}
