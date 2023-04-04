package seedu.address.logic.parser.fish;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishCommand;
import seedu.address.logic.commands.fish.FishDeleteCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class FishDeleteCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FishDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FishDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format("%s %s\n%s", FishCommand.COMMAND_WORD,
                            pe.getMessage(), FishDeleteCommand.MESSAGE_USAGE),
                    pe);
        }
    }

}
