import org.w3c.dom.Element
import java.io.File

object ViewBindingHandler {

    private val IGNORE_TAGS = arrayOf("layout")

    private const val TOOL_NAMESPACE_KEY = "xmlns:tools"
    private const val TOOL_NAMESPACE_ATTR = "http://schemas.android.com/tools"
    private const val VIEW_BINDING_IGNORE_KEY = "tools:viewBindingIgnore"
    private const val VIEW_BINDING_IGNORE_DEFAULT_VALUE = "true"

    fun handle(file: File) {
        val document = XMLHelper.readFromXml(file)
        if (checkAndUpdate(document.documentElement)) {
            XMLHelper.writeToXml(document, file)
        }
    }

    private fun checkAndUpdate(root: Element): Boolean {
        var hasChange = false

        val tagName = root.tagName
        if (!IGNORE_TAGS.contains(tagName)) {
            if (!root.hasAttribute(TOOL_NAMESPACE_KEY)) {
                root.setAttribute(TOOL_NAMESPACE_KEY, TOOL_NAMESPACE_ATTR)
                root.setAttribute(VIEW_BINDING_IGNORE_KEY, VIEW_BINDING_IGNORE_DEFAULT_VALUE)
                hasChange = true
            } else {
                if (!root.hasAttribute(VIEW_BINDING_IGNORE_KEY)) {
                    root.setAttribute(VIEW_BINDING_IGNORE_KEY, VIEW_BINDING_IGNORE_DEFAULT_VALUE)
                    hasChange = true
                }
            }
        }

        return hasChange
    }

}