package spreadsheet

import spreadsheet.StringUtils.isFormula
import spreadsheet.StringUtils.isNumeric

class Sheet {
    private val cells = mutableMapOf<String, Cell>()
    private val cellFactories: List<CellFactory> = listOf(
        CellFactory { if (it.isNumeric()) NumericConstantCell(it) else null },
        CellFactory { if (it.isFormula()) FormulaCell(it, this) else null},
        CellFactory { StringConstantCell(it) }
    )

    fun get(cell: String): String {
       return cells[cell]?.interpret()?.removeSuffix(".0") ?: ""
    }

    fun put(cell: String, value: String) {
        cells[cell] = cellFactories.fold(null) { cell: Cell?, factory ->
            cell ?: factory.build(value)
        } ?: throw IllegalArgumentException("no supported cell value")
    }

    fun getLiteral(cell: String): String {
        return cells[cell]?.literal ?: ""
    }
}
