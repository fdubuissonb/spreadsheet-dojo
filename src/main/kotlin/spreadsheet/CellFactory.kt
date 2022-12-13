package spreadsheet

fun interface CellFactory {
    fun build(content: String): Cell?
}