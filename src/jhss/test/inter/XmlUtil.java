package jhss.test.inter;

import java.io.StringReader;




import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class XmlUtil {
	/** 
     * 将xml格式的字符串转化成可以解析的Document对象 
     *  
     * @param xml 
     * @return 
     */  
    public static Document parseXmlToDocument(String xml) {  
        Document doc = null;  
        if (xml != null && !xml.equals("")) {  
            StringReader sr = new StringReader(xml);  
            InputSource is = new InputSource(sr);  
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder builder = null;  
            try {  
                builder = factory.newDocumentBuilder();  
                doc = builder.parse(is);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
  
        return doc;  
    }  
    
    
 // 从xml文件中获取节点的值  
    public static String getContentFromXml(String xml, String NodeName) {  
        return getContentFromXml(xml, NodeName, 0);  
    }  
      
    public static String getContentFromXml(String xml, String NodeName, int index) {  
        String value = "";  
        try {  
            Document doc = XmlUtil.parseXmlToDocument(xml);  
            if (doc != null) {  
                Node node = doc.getElementsByTagName(NodeName).item(index);  
                if (node != null) {  
                    value = node.getFirstChild().getTextContent();  
                }  
            }  
        } catch (Exception e) {  
            return null;  
        }  
        return value;  
    }  
    
    
}
