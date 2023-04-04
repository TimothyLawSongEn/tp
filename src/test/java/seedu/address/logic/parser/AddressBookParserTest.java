package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FISH;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditFishDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.fish.FishAddCommand;
import seedu.address.logic.commands.fish.FishCommand;
import seedu.address.logic.commands.fish.FishDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditFishDescriptorBuilder;
import seedu.address.testutil.FishBuilder;
import seedu.address.testutil.FishUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Fish fish = new FishBuilder().build();
        FishAddCommand command = (FishAddCommand) parser.parseCommand(FishUtil.getAddCommand(fish));
        FishAddCommand newCommand = new FishAddCommand(fish, Index.fromOneBased(1));
        assertEquals(newCommand, command);
    }

//    @Test
//    public void parseCommand_clear() throws Exception {
//        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
//        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
//    }

    @Test
    public void parseCommand_delete() throws Exception {
        FishDeleteCommand command = (FishDeleteCommand) parser.parseCommand(
                FishCommand.COMMAND_WORD + " " + DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FISH.getOneBased());
        assertEquals(new FishDeleteCommand(INDEX_FIRST_FISH), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Fish fish = new FishBuilder().build();
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder(fish).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FISH.getOneBased() + " " + FishUtil.getEditFishDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_FISH, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

//    @Test
//    public void parseCommand_find() throws Exception {
//        List<String> keywords = Arrays.asList("foo", "bar", "baz");
//        FindCommand command = (FindCommand) parser.parseCommand(
//                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
//        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
//    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " fishes") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " tanks") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
