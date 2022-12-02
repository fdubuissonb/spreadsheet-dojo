package spreadsheet

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SpreadsheetTestPart2 {
    private val sheet = Sheet()

    @Test
    fun testSupportsConstantFormula() {
        sheet.put("A1", "=7")
        assertEquals("=7", sheet.getLiteral("A1"))
        assertEquals("7", sheet.get("A1"))
    }

    @Test
    fun testFormulasSupportMultiplications() {
        sheet.put("A1", "=2*3*4")
        assertEquals("24", sheet.get("A1"))
    }

    @Test
    fun testFormulasSupportDivisions() {
        sheet.put("A1", "=24/3/2")
        assertEquals("4", sheet.get("A1"))
    }

    @Test
    fun testSupportFormulasMixingOperations() {
        sheet.put("A1", "=24/8*2")
        assertEquals("6", sheet.get("A1"))
    }

    @Test
    fun testFormulasSupportDecimalValues() {
        sheet.put("A1", "=10/2.5")
        assertEquals("4", sheet.get("A1"))
    }

    @Test
    fun testThatDecimalValuesAreRecognizedAsNumerics() {
        sheet.put("A1", "  2.5  ")
        assertEquals("2.5", sheet.get("A1"))

        sheet.put("A1", "  .5  ")
        assertEquals("0.5", sheet.get("A1"))

        val invalidDecimal = "  2..5  "
        sheet.put("A1", invalidDecimal)
        assertEquals("  2..5  ", sheet.get("A1"))
    }
}