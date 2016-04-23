package net.sf.jabref.gui.stringdialog;

import net.sf.jabref.logic.l10n.Localization;
import net.sf.jabref.model.database.BibDatabase;
import net.sf.jabref.model.database.KeyCollisionException;
import net.sf.jabref.model.entry.BibtexString;
import net.sf.jabref.model.entry.IdGenerator;
import net.sf.jabref.util.Util;

public class StringDialogMapped {

    public static BibtexString createNewBibtexString(String name, BibDatabase base) {
        if (name == null) {
            throw new NewStringActionException(Localization.lang("Please enter the string's label"));
        }
        if (Util.isNumber(name)) {
            throw new NewStringActionException(Localization.lang("The label of the string cannot be a number."));
        }
        if (name.contains("#")) {
            throw new NewStringActionException(
                    Localization.lang("The label of the string cannot contain the '#' character."));
        }
        if (name.contains(" ")) {
            throw new NewStringActionException(Localization.lang("The label of the string cannot contain spaces."));
        }

        try {
            String newId = IdGenerator.next();
            BibtexString bs = new BibtexString(newId, name, "");
            base.addString(bs);
            return bs;
        } catch (KeyCollisionException ex) {
            throw new NewStringActionException(Localization.lang("A string with that label already exists"));
        }
    }

    public static class NewStringActionException extends RuntimeException {
        public NewStringActionException(String error) {
            super(error);
        }
    }
}
