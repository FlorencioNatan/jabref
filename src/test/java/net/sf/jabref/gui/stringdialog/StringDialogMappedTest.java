package net.sf.jabref.gui.stringdialog;

import org.junit.Test;
import org.mockito.Mockito;

import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibtexString;
import static org.junit.Assert.*;


public class StringDialogMappedTest {

    @Test
    public void testCreateNewBibtexString() {
        BibDatabase bd = Mockito.mock(BibDatabase.class);
        BibtexString bs = StringDialogMapped.createNewBibtexString("teste", bd);

        assertEquals(bs.getName(), "teste");
        Mockito.verify(bd).addString(bs);
    }

    @Test(expected = StringDialogMapped.NewStringActionException.class)
    public void testCreateNewBibtexStringNameIsNull() {
        StringDialogMapped.createNewBibtexString(null, Mockito.mock(BibDatabase.class));
    }

    @Test(expected = StringDialogMapped.NewStringActionException.class)
    public void createNewBibtexStringNameIsNumber() {
        StringDialogMapped.createNewBibtexString("123456", Mockito.mock(BibDatabase.class));
    }

    @Test(expected = StringDialogMapped.NewStringActionException.class)
    public void testCreateNewBibtexStringNameHasSharp() {
        StringDialogMapped.createNewBibtexString("#GabrielSalvadorDaPatria", Mockito.mock(BibDatabase.class));
    }

    @Test(expected = StringDialogMapped.NewStringActionException.class)
    public void testCreateNewBibtexStringNameHasSpace() {
        StringDialogMapped.createNewBibtexString("Gabriel Salvador Da Patria", Mockito.mock(BibDatabase.class));
    }

}
