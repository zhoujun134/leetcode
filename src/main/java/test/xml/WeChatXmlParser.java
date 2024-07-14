package test.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeChatXmlParser {


    public static Map<String, String> parse(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                map.put(node.getNodeName(), node.getTextContent());
            }
        }
        return map;
    }

    public static InputStream toInputStream(String xmlString) {
        return new ByteArrayInputStream(xmlString.getBytes());
    }

    /**
     * 将 map 的数据转换为 text 的微信消息类型
     * @param data
     * @return
     */
    public static String toXmlString(Map<String, String> data) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("xml");
            doc.appendChild(root);
            for (String key : data.keySet()) {
                Element element = doc.createElement(key);
                if (StringUtils.equalsAny(key, "CreateTime", "MsgId")) {
                    element.appendChild(doc.createTextNode(data.get(key)));
                    root.appendChild(element);
                    continue;
                }
                element.appendChild(doc.createCDATASection(data.get(key)));
                root.appendChild(element);
            }
            return XmlUtils.formatXml(doc);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        final String xmlString = "<xml>\n" +
                "  <ToUserName><![CDATA[公众号的原始ID]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[用户的openid]]></FromUserName>\n" +
                "  <CreateTime>时间戳</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[这是用户发送的文本消息内容]]></Content>\n" +
                "  <MsgId>消息id，64位整型</MsgId>\n" +
                "</xml>\n";
        final InputStream inputStream = toInputStream(xmlString);
        final Map<String, String> resultMap = parse(inputStream);
        resultMap.forEach((key, value) ->
                System.out.println("key = " + key + "  value = " + value));
        final String createXmlString = toXmlString(resultMap);
        System.out.println("生成的 xml string 为：");
        System.out.println(createXmlString);

    }
}
