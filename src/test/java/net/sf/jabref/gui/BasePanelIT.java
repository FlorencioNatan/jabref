package net.sf.jabref.gui;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.entry.BibEntry;
import net.sf.jabref.model.entry.IdGenerator;


public class BasePanelIT {

    @Test
    public void testRemoveEntries() {
        BibDatabase bd = new BibDatabase();

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
    }

}
