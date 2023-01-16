package com.socketLabs.injectionApi.message;

/**
 * Represents a metadata entry as a key and value pair.
 * {@code
 *<p>
 * Example:
 * <pre>
 *     Metadata metadata1 = new Metadata();
 *     metadata1.setKey("key1");
 *     metadata1.setValue("value1");
 *
 *     Metadata metadata2 = new Metadata("key1", "value1");
 * </pre>
 * </p>
 * }
 */
public class Metadata {

    /**
     * The metadata key.
     */
    private String key;
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
     * Creates a new instance of the Metadata class and sets the key and value pair.
     * @param key String
     * @param value String
     */
    public Metadata(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get the metadata key.
     * @return String
     */
    public String getKey() {
        return this.key;
    }
    /**
     * Set the metadata key.
     * @param value String
     */
    public void setKey(String value) {
        this.key = value;
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

        if (this.key == null || this.key.isEmpty()) {
            return false;
        }
        if (this.value == null || this.value.isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Returns the metadata as a key-value pair, useful for debugging.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s: %s", this.key, this.value);
    }
}
