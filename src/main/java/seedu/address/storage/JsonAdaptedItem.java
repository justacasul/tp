package seedu.address.storage;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Item;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Item}.
 */
class JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    // Identity fields
    private final int id;
    private final String name;

    // Data fields
    private final int quantity;
    private final String description;
    private final List<Integer> locationId = new ArrayList<>();
    private final List<Integer> recipeId = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final boolean isDeleted;

    /**
     * Constructs a {@code JsonAdaptedItem} with the given item details.
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("id") int id,
                           @JsonProperty("name") String name,
                           @JsonProperty("quantity") int quantity,
                           @JsonProperty("description") String description,
                           @JsonProperty("locationId") List<Integer> locationId,
                           @JsonProperty("recipeId") List<Integer> recipeId,
                           @JsonProperty("tags") List<JsonAdaptedTag> tagged,
                           @JsonProperty("isDeleted") boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.isDeleted = isDeleted;
        if (locationId != null) {
            this.locationId.addAll(locationId);
        }
        if (recipeId != null) {
            this.recipeId.addAll(recipeId);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        id = source.getId();
        name = source.getName();
        quantity = source.getQuantity();
        description = source.getDescription();
        isDeleted = source.isDeleted();
        locationId.addAll(new ArrayList<>(source.getLocationId()));
        recipeId.addAll(new ArrayList<>(source.getRecipeId()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public Item toModelType() throws IllegalValueException {
        final List<Tag> itemTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            itemTags.add(tag.toModelType());
        }

        final Set<Tag> modelTags = new HashSet<>(itemTags);
        return new Item(id, name, quantity, description, locationId,
                recipeId, modelTags, isDeleted);
    }

}