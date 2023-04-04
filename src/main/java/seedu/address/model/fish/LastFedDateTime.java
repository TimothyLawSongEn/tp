package seedu.address.model.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import seedu.address.model.date.DateUtil;

/**
 * Represents a Fish's last fed date time number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class LastFedDateTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Last Fed Date Time is a date time in the format of \"dd/MM/yyyy HH:mm\"";
    public static final String MESSAGE_CONSTRAINTS_NOT_FUTURE =
            "Last Fed Date Time cannot be in the future.";
    public static final String VALIDATION_REGEX =
            "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((?!00)[0-9]{2}(?!00)"
                    + "|[0-9]{4}) (?:[01][0-9]|2[0-3]):[0-5][0-9]$";
    //     "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} (?:[01]\\d|2[0-3]):[0-5]\\d$";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    .withResolverStyle(ResolverStyle.SMART);

    public final String value;
    public final LocalDateTime localDateTime;
    public final String alphaNumericDateTime;

    /**
     * Constructs a {@code LastFedDate}.
     *
     * @param lastFedDateTime A valid last Fed Date number.
     */
    public LastFedDateTime(String lastFedDateTime) {
        requireNonNull(lastFedDateTime);
        checkArgument(isValidDateTime(lastFedDateTime), MESSAGE_CONSTRAINTS);

        value = lastFedDateTime;
        localDateTime = LocalDateTime.parse(lastFedDateTime, formatter);


        checkArgument(isNotFuture(lastFedDateTime), MESSAGE_CONSTRAINTS_NOT_FUTURE);

        alphaNumericDateTime = DateUtil.getTaskDescriptionDateTimeFormat(localDateTime);
    }

    /**
     * Returns true if a given string is a valid last Fed Date number.
     */
    public static boolean isValidDateTime(String test) {

        //        LocalDateTime dateTime = LocalDateTime.parse(test, formatter);
        //
        //        // Extract just the date (dd/MM/yyyy) from the LocalDateTime object
        //        LocalDate date = dateTime.toLocalDate();
        //
        //        // Format the date as a string in the format of "dd/MM/yyyy"
        //        String outputDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        //
        //        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //        dateFormat.setLenient(false);
        //
        //        try {
        //            Date datee = dateFormat.parse(outputDate);
        //            System.out.println("Valid date: " + date);
        //        } catch (ParseException e) {
        //            System.out.println("Invalid date: " + outputDate);
        //        }

        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if given string parses to a DateTime not in the future.
     */
    public static boolean isNotFuture(String test) {
        LocalDateTime localDateTime = LocalDateTime.parse(test, formatter);
        return !localDateTime.isAfter(LocalDateTime.now());
    }

    public String getAlphaNumericDateTime() {
        return this.alphaNumericDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastFedDateTime // instanceof handles nulls
                && localDateTime.equals(((LastFedDateTime) other).localDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
