package net.sf.jabref.gui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import net.sf.jabref.model.database.BibDatabase;


public class PreambleEditorTest {

    @Test
    public void testSetPreamble() {
        BibDatabase bd = Mockito.mock(BibDatabase.class);
        assertEquals(null, PreambleEditorMapped.setPreamble("random entry preamble", bd));
        Mockito.verify(bd).getPreamble();
    }

    @Test
    public void testWithNullEntry() {
        BibDatabase bd = Mockito.mock(BibDatabase.class);
        assertEquals(null, PreambleEditorMapped.setPreamble(null, bd));
    }

}
