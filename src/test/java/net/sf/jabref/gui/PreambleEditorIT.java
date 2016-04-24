package net.sf.jabref.gui;

import static org.junit.Assert.*;

import org.junit.Test;
import net.sf.jabref.model.database.BibDatabase;


public class PreambleEditorIT {

    @Test
    public void testSetPremableCheckReturnOldPreamble() {
        BibDatabase bd = new BibDatabase();
        assertEquals(null, PreambleEditorMapped.setPreamble("random entry preamble", bd));
        assertEquals("random entry preamble", PreambleEditorMapped.setPreamble(null, bd));
    }

    @Test
    public void testInsertNewPreambleWhenPreambleIsNotNull() {
        BibDatabase bd = new BibDatabase();
        assertEquals(null, PreambleEditorMapped.setPreamble("random entry preamble", bd));
        assertEquals("random entry preamble", PreambleEditorMapped.setPreamble("new preamble", bd));
    }

}
