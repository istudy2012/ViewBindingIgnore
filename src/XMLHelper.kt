import org.w3c.dom.Document
import java.io.File
import java.io.FileOutputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

object XMLHelper {

    @JvmStatic
    fun readFromXml(file: File): Document {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        return builder.parse(file)
    }

    @JvmStatic
    fun writeToXml(root: Document, file: File) {
        val transFactory = TransformerFactory.newInstance();
        val transFormer = transFactory.newTransformer()
        val domSource = DOMSource(root)

        val out = FileOutputStream(file)
        transFormer.setOutputProperty(OutputKeys.INDENT, "yes")
        val xmlResult = StreamResult(out)
        transFormer.transform(domSource, xmlResult);
    }

}