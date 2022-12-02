package spreadsheet

object StringUtils {
    private val numericRegex = Regex("""\s*\d*\.?\d+\s*""")

    fun String.isNumeric() = numericRegex.matches(this)
}