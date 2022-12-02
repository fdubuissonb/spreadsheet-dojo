package spreadsheet

import kotlin.test.Test
import kotlin.test.assertEquals

class SpreadsheetTestPart1 {
    private val sheet = Sheet()

    @Test
    fun testThatCellsAreEmptyByDefault() {
        assertEquals("", sheet.get("A1"))
        assertEquals("", sheet.get("ZX347"))
    }
/*
    @Test
    fun testThatCellsAreStored() {
        val theCell = "A21"

        sheet.put(theCell, "A string")
        assertEquals("A string", sheet.get(theCell))

        sheet.put(theCell, "A different string")
        assertEquals("A different string", sheet.get(theCell))

        sheet.put(theCell, "")
        assertEquals("", sheet.get(theCell))
    }

    @Test
    fun testThatManyCellsExist() {
        sheet.put("A1", "First")
        sheet.put("X27", "Second")
        sheet.put("ZX901", "Third")

        assertEquals("First", sheet.get("A1"))
        assertEquals("Second", sheet.get("X27"))
        assertEquals("Third", sheet.get("ZX901"))

        sheet.put("A1", "Fourth")
        assertEquals("Fourth", sheet.get("A1"))
        assertEquals("Second", sheet.get("X27"))
        assertEquals("Third", sheet.get("ZX901"))
    }

    @Test
    fun testThatCellLiteralValuesArePreservedForEditing() {
        val theCell = "A21"

        sheet.put(theCell, "Some string")
        assertEquals("Some string", sheet.getLiteral(theCell))

        sheet.put(theCell, " 1234 ")
        assertEquals(" 1234 ", sheet.getLiteral(theCell))

        sheet.put(theCell, "=7") // Foreshadowing formulas:)
        assertEquals("=7", sheet.getLiteral(theCell))
    }

    @Test
    fun testThatNumericCellsAreRecognized() {
        val theCell = "A21"

        sheet.put(theCell, "X99") // "Obvious" string
        assertEquals("X99", sheet.get(theCell))

        sheet.put(theCell, "14") // "Obvious" number
        assertEquals("14", sheet.get(theCell))

        sheet.put(theCell, " 99 X") // Whole string must be numeric
        assertEquals(" 99 X", sheet.get(theCell))

        sheet.put(theCell, " 1234 ") // Blanks ignored
        assertEquals("1234", sheet.get(theCell))

        sheet.put(theCell, " ") // Just a blank
        assertEquals(" ", sheet.get(theCell))
    }*/
}