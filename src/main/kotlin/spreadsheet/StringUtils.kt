package spreadsheet

object StringUtils {
    private val numericRegex = Regex("""\s*\d*\.?\d+\s*""")

    fun String.isNumeric() = numericRegex.matches(this)

    fun String.isFormula() = trim().let {
        this.isNotBlank() && this[0] == '='
    }
}