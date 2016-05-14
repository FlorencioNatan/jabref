package br.com.ufs.ds3.grupo2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import net.sf.jabref.BibDatabaseContext;
import net.sf.jabref.Defaults;
import net.sf.jabref.gui.BasePanel;
import net.sf.jabref.model.EntryTypes;
import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.database.BibDatabaseMode;
import net.sf.jabref.model.entry.BibEntry;
import net.sf.jabref.model.entry.EntryType;
import net.sf.jabref.model.entry.IdGenerator;

public class BasePanelTest {

    @Test
    public void testNewEntry() {
        for (int i = 0; i < 2; i++) {
            int numberOfTests = 0;
            //Verify for both database types
            Defaults defs = new Defaults(i == 0 ? BibDatabaseMode.BIBTEX : BibDatabaseMode.BIBLATEX);
            BibDatabase dataBase = new BibDatabase();
            BibDatabaseContext context = new BibDatabaseContext(dataBase, defs);

            Collection<EntryType> types = EntryTypes.getAllValues(dataBase.getBibType());
            Iterator<EntryType> it = types.iterator();

            //Verify for all kind of EntryType
            while (it.hasNext()) {
                EntryType type = it.next();
                BibEntry be = BasePanel.newEntry(type, dataBase);
                assertNotNull(be);
                assertTrue(dataBase.getEntries().contains(be));
                numberOfTests++;
            }
            assertEquals(dataBase.getEntryCount(), numberOfTests);
        }
    }

    @Test
    public void testRemoveEntries() {
        BibDatabase bd = Mockito.mock(BibDatabase.class);

        BibEntry be0 = new BibEntry(IdGenerator.next(), "Teste");
        BibEntry be1 = new BibEntry(IdGenerator.next(), "for");
        BibEntry be2 = new BibEntry(IdGenerator.next(), "remove");
        BibEntry be3 = new BibEntry(IdGenerator.next(), "bibtex");
        BibEntry be4 = new BibEntry(IdGenerator.next(), "entries");

        bd.insertEntry(be0);
        bd.insertEntry(be1);
        bd.insertEntry(be2);
        bd.insertEntry(be3);
        bd.insertEntry(be4);

        List<BibEntry> entries = new ArrayList<>();
        entries.add(be0);
        entries.add(be1);

        List<BibEntry> removed = BasePanel.removeEntries(entries, bd);

        assertEquals(removed.get(0).getId(), be0.getId());
        assertEquals(removed.get(1).getId(), be1.getId());

        Mockito.verify(bd).removeEntry(be0);
        Mockito.verify(bd).removeEntry(be1);
    }

}
