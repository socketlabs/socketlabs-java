package com.socketLabs.injectionApi.message;

/**
 * Represents a custom header as a name and value pair.
 * {@code
 *<p>
 * Example:
 * <pre>
 *     CustomHeader header1 = new CustomHeader();
 *     header1.setName("name1");
 *     header1.setValue("value1");
 *
 *     CustomHeader header2 = new CustomHeader("name1", "value1");
 * </pre>
 * </p>
 * }
 */
public class CustomHeader {

    /**
     * The custom header name.
     */
    private String name;
    /**
     * The custom header value.
     */
    private String value;

    /**
     * Creates a new instance of the CustomHeader class.
     */
    public CustomHeader() {
    }

    /**
     * Creates a new instance of the CustomHeader class and sets the name and value pair.
     * @param name String
     * @param value String
     */
    public CustomHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Get the custom header name.
     * @return String
     */
    public String getName() {
        return this.name;
    }
    /**
     * Set the custom header name.
     * @param value String
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Get the custom header value.
     * @return String
     */
    public String getValue() {
        return this.value;
    }
    /**
     * Set the custom header value.
     * @param value String
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * A quick check to ensure that the custom header is valid.
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
     * Returns the custom header as a name-value pair, useful for debugging.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s: %s", this.name, this.value);
    }
}
