package spreadsheet

import spreadsheet.StringUtils.isNumeric

class Formula(
    input: String,
    private val sheet: Sheet,
) {
    private val rootItem = buildFormulaItem(input.trim())

    fun interpret() = rootItem.interpret()

    private fun buildFormulaItem(input: String): FormulaItem {
        return extractFormulaItem(input, '*') { item1, item2 -> MultiplyFormulaItem(item1, item2) }
            ?: extractFormulaItem(input, '/') { item1, item2 -> DivideFormulaItem(item1, item2) }
            ?: extractFormulaItem(input, '+') { item1, item2 -> AdditionFormulaItem(item1, item2) }
            ?: if (input.isNumeric()) ConstantFormulaItem(input.toFloat()) else ReferenceFormulaItem(CellReference(sheet, input))
    }

    private fun extractFormulaItem(input: String, operator: Char, builder: (FormulaItem, FormulaItem) -> FormulaItem): FormulaItem? {
        val index = input.lastIndexOf(operator)
        return if (index != -1) {
            builder(
                buildFormulaItem(input.substring(0, index).trim()),
                buildFormulaItem(input.substring(index + 1).trim())
            )
        } else {
            null
        }
    }
}

sealed interface FormulaItem {
    fun interpret(): Float
}

class ConstantFormulaItem(
    private val value: Float
) : FormulaItem {
    override fun interpret() = value
}

class ReferenceFormulaItem(
    private val cell: CellReference
): FormulaItem {
    override fun interpret() = cell.getValue().toFloat() // TODO: handle string values ?
}

class MultiplyFormulaItem(
    private val operand1: FormulaItem,
    private val operand2: FormulaItem,
) : FormulaItem {
    override fun interpret() = operand1.interpret() * operand2.interpret()
}

class DivideFormulaItem(
    private val operand1: FormulaItem,
    private val operand2: FormulaItem,
) : FormulaItem {
    override fun interpret() = operand1.interpret() / operand2.interpret()
}

class AdditionFormulaItem(
    private val operand1: FormulaItem,
    private val operand2: FormulaItem,
) : FormulaItem {
    override fun interpret() = operand1.interpret() + operand2.interpret()
}

data class CellReference(
    val sheet: Sheet,
    val cell: String,
) {
    fun getValue() = sheet.get(cell)
}