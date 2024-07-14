package test.xml;

import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class XmlUtils {
    /**
     * 将 XML 文档对象格式化为字符串
     * @param doc 要格式化的 XML 文档对象
     * @return 格式化后的字符串
     */
    public static String formatXml(Document doc) {
        try {
            // 创建一个 TransformerFactory 对象，用于创建 Transformer 对象
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            // 设置缩进数为 2
            transformerFactory.setAttribute("indent-number", 2);
            // 创建一个 Transformer 对象，用于将 XML 文档对象转换为字符串
            Transformer transformer = transformerFactory.newTransformer();
            // 设置输出编码为 UTF-8
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // 开启缩进和换行
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建一个 StringWriter 对象，用于存储转换后的字符串
            StringWriter writer = new StringWriter();
            // 将 XML 文档对象转换为 StreamResult 对象，输出到 StringWriter 中
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            // 返回 StringWriter 中的字符串
            return writer.toString();
        } catch (TransformerException e) {
            // 抛出运行时异常，不处理异常信息
            throw new RuntimeException(e);
        }
    }
}
