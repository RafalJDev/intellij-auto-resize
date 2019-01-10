import java.util.function.Consumer
import javax.swing.JTree

object ResizerUtil {

    @JvmStatic
    fun calculateDesiredLength(jTree: JTree, longestPathIndex: Int): Int {
        return jTree.getPathBounds(jTree.getPathForRow(longestPathIndex))!!.width + jTree.getPathBounds(
                jTree.getPathForRow(
                        longestPathIndex))!!.x
    }

    @JvmStatic
    fun longestPathValueOfTree(jTree: JTree): Int {
        val list = (0 until jTree.rowCount)
                .map { rowIndex ->
                    jTree.getPathForRow(rowIndex)
                            .toString()
                            .length
                }
                .toList()

        println("List length:")
        list.forEach(Consumer<Int> { println(it) })

        var max = list[0]
        for (integer in list) {
            if (integer > max) {
                max = integer
            }
        }

        return list.indexOf(max)
    }

    @JvmStatic
    fun printPathTree(jTree: JTree) {
        println("tree.getRowCount() = " + jTree.rowCount)

        println("-----------------")
        println("Paths with for parents")
        println("-----------------")

        (0 until jTree.rowCount)
                .forEach { value ->
                    println(value.toString() + " name= " + jTree.getPathForRow(value)
                            .parentPath
                            .toString())
                }

        println("-----------------")
        println("Path for childs")
        println("-----------------")

        (0 until jTree.rowCount)
                .forEach { value ->
                    println(value.toString() + " name= " + jTree.getPathForRow(value)
                            .toString())
                }

        println("-----------------")
    }
}