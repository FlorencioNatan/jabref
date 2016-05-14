package br.com.ufs.ds3.grupo2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import net.sf.jabref.gui.stringdialog.StringDialogMapped;
import net.sf.jabref.gui.stringdialog.StringDialogMapped.NewStringActionException;
import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibtexString;


public class StringDialogMappedIT {

    @Test
    public void testCreateNewBibtexString() {
        BibtexString bs = StringDialogMapped.createNewBibtexString("teste", new BibDatabase());
        assertEquals(bs.getName(), "teste");
    }

    @Test(expected = StringDialogMapped.NewStringActionException.class)
    public void testCreateNewBibtexStringKeyColision() {
        BibDatabase bd = new BibDatabase();
        StringDialogMapped.createNewBibtexString("TesteIgual", bd);
        StringDialogMapped.createNewBibtexString("TesteIgual", bd);
    }

    @Test
    public void testRemoveStringFromBibtexString() {
        BibDatabase bd = new BibDatabase();
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
    }

}
