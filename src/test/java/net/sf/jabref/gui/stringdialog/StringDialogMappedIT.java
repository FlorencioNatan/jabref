package net.sf.jabref.gui.stringdialog;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibtexString;


public class StringDialogMappedIT {

    @Test
    public void createNewBibtexString() {
        BibtexString bs = StringDialogMapped.createNewBibtexString("teste", new BibDatabase());
        assertEquals(bs.getName(), "teste");
    }

    @Test(expected = StringDialogMapped.NewStringActionException.class)
    public void createNewBibtexStringKeyColision() {
        BibDatabase bd = new BibDatabase();
        StringDialogMapped.createNewBibtexString("TesteIgual", bd);
        StringDialogMapped.createNewBibtexString("TesteIgual", bd);
    }


}
