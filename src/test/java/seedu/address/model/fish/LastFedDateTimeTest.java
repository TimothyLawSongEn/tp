package seedu.address.model.fish;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LastFedDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastFedDateTime(null));
    }

    @Test
    public void constructor_invalidLastFedDate_throwsIllegalArgumentException() {
        String invalidLastFedDate = "";
        assertThrows(IllegalArgumentException.class, () -> new LastFedDateTime(invalidLastFedDate));
    }

    @Test
    public void isValidLastFedDate() {
        // null date
        assertThrows(NullPointerException.class, () -> LastFedDateTime.isValidDateTime(null));

        // invalid date
        assertFalse(LastFedDateTime.isValidDateTime("")); // empty string
        assertFalse(LastFedDateTime.isValidDateTime(" ")); // spaces only
        assertFalse(LastFedDateTime.isValidDateTime("91")); // less than 3 numbers
        assertFalse(LastFedDateTime.isValidDateTime("01.01.2000 00:00")); // format is dd/mm/yyyy
        assertFalse(LastFedDateTime.isValidDateTime("1a/05/2000 00:00")); // alphabets within digits
        assertFalse(LastFedDateTime.isValidDateTime("32/01/2023 00:00")); // invalid date

        // valid dates
        assertTrue(LastFedDateTime.isValidDateTime("12/12/2000 00:00")); // exactly 3 numbers
        assertTrue(LastFedDateTime.isValidDateTime("05/05/2022 00:00"));
        assertTrue(LastFedDateTime.isValidDateTime("06/05/2021 00:00"));
    }
}
