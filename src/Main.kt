import java.io.File

object Main {

    // Config Layouts, add tools:viewBindingIgnore="true" to the xml
    private val LAYOUT_DIRS = arrayOf("app/src/main/res/layout/")

    @JvmStatic
    fun main(arg: Array<String>) {
        getXmlFileList()
            .forEach {
                if (it.exists()) {
                    ViewBindingHandler.handle(it)
                }
            }
    }

    private fun getXmlFileList(): List<File> {
        return LAYOUT_DIRS.flatMap {
            val file = File(it)
            if (file.isDirectory) {
                file.listFiles()?.toList() ?: arrayListOf()
            } else {
                arrayListOf(file)
            }
        }
    }
}