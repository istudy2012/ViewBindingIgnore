import java.io.File
import java.util.concurrent.Executors

object Main {

    // Config Layouts, add tools:viewBindingIgnore="true" to the xml
    private val LAYOUT_DIRS = arrayOf("app/src/main/res/layout/", "business/src/main/res/layout/")

    private val executor = Executors.newFixedThreadPool(4)

    @JvmStatic
    fun main(arg: Array<String>) {
        getXmlFileList()
            .forEach {
                if (it.exists()) {
                    executor.execute {
                        ViewBindingHandler.handle(it)
                    }
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