package com.socketLabs.injectionApi.message;

/**
 * Represents a metadata entry as a name and value pair.
 * {@code
 *<p>
 * Example:
 * <pre>
 *     Metadata metadata1 = new Metadata();
 *     metadata1.setName("name1");
 *     metadata1.setValue("value1");
 *
 *     Metadata metadata2 = new Metadata("name1", "value1");
 * </pre>
 * </p>
 * }
 */
public class Metadata {

    /**
     * The metadata name.
     */
    private String name;
    /**
     * The metadata value.
     */
    private String value;

    /**
     * Creates a new instance of the Metadata class.
     */
    public Metadata() {
    }

    /**
     * Creates a new instance of the Metadata class and sets the name and value pair.
     * @param name String
     * @param value String
     */
    public Metadata(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Get the metadata name.
     * @return String
     */
    public String getName() {
        return this.name;
    }
    /**
     * Set the metadata name.
     * @param value String
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Get the metadata value.
     * @return String
     */
    public String getValue() {
        return this.value;
    }
    /**
     * Set the metadata value.
     * @param value String
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * A quick check to ensure that the metadata is valid.
     * @return boolean
     */
    public boolean isValid() {

        if (this.name == null || this.name.isEmpty()) {
            return false;
        }
        if (this.value == null || this.value.isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Returns the metadata as a name-value pair, useful for debugging.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s: %s", this.name, this.value);
    }
}
