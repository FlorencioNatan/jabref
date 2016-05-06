package net.sf.jabref.model.entry;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;


public class IEEETranEntryTypesTest {

    @Test
    public void allRequiredFieldsPresentStandardOR() {
        BibEntry e = new BibEntry("id", IEEETranEntryTypes.STANDARD);
        e.setField("title", "abc");
        e.setField("organization", "abc");

        List<String> requiredFields = new ArrayList<>();

        requiredFields.add("title");
        requiredFields.add("organization/institution");
        Assert.assertTrue(e.allFieldsPresent(requiredFields, null));

        requiredFields.add("day");
        Assert.assertFalse(e.allFieldsPresent(requiredFields, null));
    }

    @Test
    public void allRequiredFieldsPresentPeriodical() {
        BibEntry e = new BibEntry("id", IEEETranEntryTypes.PERIODICAL);
        e.setField("title", "abc");
        e.setField("year", "abc");

        List<String> requiredFields = new ArrayList<>();

        requiredFields.add("title");
        requiredFields.add("year");
        Assert.assertTrue(e.allFieldsPresent(requiredFields, null));

        requiredFields.add("editor");
        Assert.assertFalse(e.allFieldsPresent(requiredFields, null));

    }

}
