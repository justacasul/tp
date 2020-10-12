package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_INGREDIENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.RecipeList;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Persons
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    // items
    public static final String VALID_ITEM_NAME_APPLE = "Apple";
    public static final String VALID_ITEM_NAME_BANANA = "Banana";
    public static final String VALID_ITEM_QUANTITY_APPLE = "9";
    public static final String VALID_ITEM_QUANTITY_BANANA = "99";
    public static final String VALID_ITEM_DESCRIPTION_APPLE = "Recovers 10 hp";
    public static final String VALID_ITEM_DESCRIPTION_BANANA = "Used as bait";
    public static final String VALID_ITEM_LOCATION_PEACH_ORCHARD = "Bob's peach orchard";
    public static final String VALID_ITEM_LOCATION_SPINACH_GARDEN = "Bob's spinach garden";

    public static final String VALID_RECIPE_PRODUCT_NAME_APPLE_PIE = "Apple Pie";
    public static final String VALID_RECIPE_PRODUCT_NAME_BANANA_PIE = "Banana Pie";
    public static final int VALID_RECIPE_ID_TWO = 2;
    public static final String VALID_RECIPE_QUANTITY_APPLE_PIE = "1";
    public static final String VALID_RECIPE_QUANTITY_APPLE_PIE_ALTERNATE = "2";
    public static final String VALID_RECIPE_QUANTITY_BANANA_PIE = "3";
    public static final String VALID_RECIPE_DESC_APPLE_PIE = "Apple-y!";
    public static final String VALID_RECIPE_DESC_APPLE_PIE_ALTERNATE = "Not Apple-y!";
    public static final String VALID_RECIPE_DESC_BANANA_PIE = "Banana-y!";
    public static final String VALID_RECIPE_INGREDIENTS_APPLE_PIE = "Apple[1] Banana[2]";
    public static final String VALID_RECIPE_INGREDIENTS_BANANA_PIE = "Apple[2] Banana[1]";
    public static final String INVALID_RECIPE_QUANTITY_APPLE_PIE = "-1";

    public static final String ITEM_NAME_DESC_APPLE = " "
            + PREFIX_ITEM_NAME + VALID_ITEM_NAME_APPLE;
    public static final String ITEM_NAME_DESC_BANANA = " "
            + PREFIX_ITEM_NAME + VALID_ITEM_NAME_BANANA;
    public static final String ITEM_QUANTITY_DESC_APPLE = " "
            + PREFIX_ITEM_QUANTITY + VALID_ITEM_QUANTITY_APPLE;
    public static final String ITEM_QUANTITY_DESC_BANANA = " "
            + PREFIX_ITEM_QUANTITY + VALID_ITEM_QUANTITY_BANANA;
    public static final String ITEM_DESCRIPTION_DESC_APPLE = " "
            + PREFIX_ITEM_DESCRIPTION + VALID_ITEM_DESCRIPTION_APPLE;
    public static final String ITEM_DESCRIPTION_DESC_BANANA = " "
            + PREFIX_ITEM_DESCRIPTION + VALID_ITEM_DESCRIPTION_BANANA;
    public static final String ITEM_LOCATION_DESC_PEACH_ORCHARD = " "
            + PREFIX_ITEM_LOCATION + VALID_ITEM_LOCATION_PEACH_ORCHARD;
    public static final String ITEM_LOCATION_DESC_SPINACH_GARDEN = " "
            + PREFIX_ITEM_LOCATION + VALID_ITEM_LOCATION_SPINACH_GARDEN;

    public static final String RECIPE_PRODUCT_NAME_APPLE_PIE = " "
            + PREFIX_RECIPE_PRODUCT_NAME + VALID_RECIPE_PRODUCT_NAME_APPLE_PIE;
    public static final String RECIPE_PRODUCT_NAME_BANANA_PIE = " "
            + PREFIX_RECIPE_PRODUCT_NAME + VALID_RECIPE_PRODUCT_NAME_BANANA_PIE;
    public static final String RECIPE_QUANTITY_APPLE_PIE = " "
            + PREFIX_RECIPE_PRODUCT_QUANTITY + VALID_RECIPE_QUANTITY_APPLE_PIE;
    public static final String RECIPE_QUANTITY_BANANA_PIE = " "
            + PREFIX_RECIPE_PRODUCT_QUANTITY + VALID_RECIPE_QUANTITY_BANANA_PIE;
    public static final String RECIPE_DESCRIPTION_APPLE_PIE = " "
            + PREFIX_RECIPE_DESCRIPTION + VALID_RECIPE_DESC_APPLE_PIE;
    public static final String RECIPE_DESCRIPTION_BANANA_PIE = " "
            + PREFIX_RECIPE_DESCRIPTION + VALID_RECIPE_DESC_BANANA_PIE;
    public static final String RECIPE_INGREDIENTS_APPLE_PIE = " "
            + PREFIX_RECIPE_INGREDIENTS + VALID_RECIPE_INGREDIENTS_APPLE_PIE;
    public static final String RECIPE_INGREDIENTS_BANANA_PIE = " "
            + PREFIX_RECIPE_INGREDIENTS + VALID_RECIPE_INGREDIENTS_BANANA_PIE;
    public static final String INVALID_RECIPE_QUANTITY = " "
            + PREFIX_RECIPE_PRODUCT_QUANTITY + INVALID_RECIPE_QUANTITY_APPLE_PIE;

    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_ITEM_QUANTITY + "9a"; // 'a' not allowed in quantity

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Version for InventoryListCommands
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the item list, filtered item list, recipe list, filtered recipe list,
     *   location list, filtered location list {@code actualModel} remain unchanged
     * @param command Command to execute
     * @param actualModel the actual model
     * @param expectedMessage expected error message from execution
     */
    public static void assertInventoryCommandFailure(Command command, Model actualModel, String expectedMessage) {
        ItemList expectedItemList = new ItemList(actualModel.getItemList());
        List<Item> expectedFilteredItemList = new ArrayList<>(actualModel.getFilteredItemList());
        RecipeList expectedRecipeList = new RecipeList(actualModel.getRecipeList());
        List<Recipe> expectedFilteredRecipeList = new ArrayList<>(actualModel.getFilteredRecipeList());
        LocationList expectedLocationList = new LocationList(actualModel.getLocationList());
        List<Location> expectedFilteredLocationList = new ArrayList<>(actualModel.getFilteredLocationList());
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedItemList, actualModel.getItemList());
        assertEquals(expectedFilteredItemList, actualModel.getFilteredItemList());
        assertEquals(expectedRecipeList, actualModel.getRecipeList());
        assertEquals(expectedFilteredRecipeList, actualModel.getFilteredRecipeList());
        assertEquals(expectedLocationList, actualModel.getLocationList());
        assertEquals(expectedFilteredLocationList, actualModel.getFilteredLocationList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
