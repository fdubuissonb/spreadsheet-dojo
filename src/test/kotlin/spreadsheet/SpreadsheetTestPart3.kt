package spreadsheet

import kotlin.test.Test
import kotlin.test.assertEquals

class SpreadsheetTestPart3 {
    private val sheet = Sheet()

    @Test
    fun testThatCellCanReferenceOtherCells() {
        sheet.put("A1", "8")
        sheet.put("A2", "=A1")
        assertEquals("8", sheet.get("A2"))
    }

    @Test
    fun testThatCellChangesPropagate() {
        sheet.put("A1", "8")
        sheet.put("A2", "=A1")
        sheet.put("A1", "9")
        assertEquals("9", sheet.get("A2"))
    }

    @Test
    fun testThatFormulasKnowCellsAndRecalculate() {
        sheet.put("A1", "8")
        sheet.put("A2", "3")
        sheet.put("B1", "=A1*A2/4")
        assertEquals("6", sheet.get("B1"))

        sheet.put("A2", "6")
        assertEquals("12", sheet.get("B1"))
    }

    @Test
    fun testThatDeepPropagationWorks() {
        sheet.put("A1", "8")
        sheet.put("A2", "=A1")
        sheet.put("A3", "=A2")
        sheet.put("A4", "=A3")
        assertEquals("8", sheet.get("A4"))

        sheet.put("A2", "6")
        assertEquals("6", sheet.get("A4"))
    }

    @Test
    fun testThatFormulaWorksWithManyCells() {
        sheet.put("A1", "10")
        sheet.put("A2", "=4")
        sheet.put("A3", "=A1*A2")
        sheet.put("A4", "=A3")
        assertEquals("40", sheet.get("A4"))
    }

    @Test
    fun testThatSpacesAreAllowedInFormulas() {
        sheet.put("A1", "10")
        sheet.put("B1", "7")
        sheet.put("A2", "= A1 * B1")
        assertEquals("70", sheet.get("A2"))
    }
}