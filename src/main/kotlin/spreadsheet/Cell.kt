package spreadsheet

sealed interface Cell {
    val literal: String

    fun interpret(): String
}

class StringConstantCell(
    private val value: String
) : Cell {
    override val literal: String =  value

    override fun interpret() = value
}

class NumericConstantCell(
    value: String
) : Cell {
    private val numericValue = value.trim().toFloat()

    override val literal: String =  value

    override fun interpret() = numericValue.toString()
}

class FormulaCell(
    value: String,
    sheet: Sheet
): Cell {
    private val formula = Formula(value.trim().substring(1), sheet)
    override val literal: String = value

    override fun interpret() = formula.interpret().toString()
}