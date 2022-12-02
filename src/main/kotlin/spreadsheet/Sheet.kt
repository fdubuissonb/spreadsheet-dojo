package spreadsheet

import spreadsheet.StringUtils.isNumeric

class Sheet {
    private val cells = mutableMapOf<String, Cell>()

    fun get(cell: String): String {
       return cells[cell]?.interpret()?.removeSuffix(".0") ?: ""
    }

    fun put(cell: String, value: String) {
        cells[cell] = when {
            value.isNumeric() -> NumericConstantCell(value)
            value.isFormula() -> FormulaCell(value, this)
            else -> StringConstantCell(value)
        }
    }

    fun getLiteral(cell: String): String {
        return cells[cell]?.literal ?: ""
    }


    private fun String.isFormula() = trim().let {
        this.isNotBlank() && this[0] == '='
    }
}
