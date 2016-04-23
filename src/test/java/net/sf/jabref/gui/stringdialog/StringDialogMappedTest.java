package net.sf.jabref.gui.stringdialog;

import org.junit.Test;
import org.mockito.Mockito;

import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibtexString;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
    public void testCreateNewBibtexStringNameIsNumber() {
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

    @Test
    public void testRemoveStringFromBibtexString() {
        BibDatabase bd = Mockito.mock(BibDatabase.class);
        List<BibtexString> strings = new ArrayList<>();
        BibtexString bs0 = StringDialogMapped.createNewBibtexString("Teste", bd);
        BibtexString bs1 = StringDialogMapped.createNewBibtexString("for", bd);
        BibtexString bs2 = StringDialogMapped.createNewBibtexString("remove", bd);
        BibtexString bs3 = StringDialogMapped.createNewBibtexString("bibtex", bd);
        BibtexString bs4 = StringDialogMapped.createNewBibtexString("strings", bd);

        strings.add(bs0);
        strings.add(bs1);
        strings.add(bs2);
        strings.add(bs3);
        strings.add(bs4);
        int[] rows = {0, 1, 3};

        HashMap<Integer, BibtexString> removed = StringDialogMapped.removeStringFromBibtexString(strings, rows, bd);

        assertEquals(removed.get(0).getName(), "Teste");
        assertEquals(removed.get(1).getName(), "for");
        assertEquals(removed.get(3).getName(), "bibtex");
        Mockito.verify(bd).removeString(bs0.getId());
        Mockito.verify(bd).removeString(bs1.getId());
        Mockito.verify(bd).removeString(bs3.getId());
    }

}
