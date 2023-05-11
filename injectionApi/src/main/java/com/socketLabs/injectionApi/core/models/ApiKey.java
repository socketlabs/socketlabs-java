package com.socketLabs.injectionApi.core.models;
/**
 *  The object representing the Api Key.
 */
public class ApiKey {
    
    /** Public part of the Api Key. */
    @JsonProperty("PublicPart")
    private String publicPart;

    /** Private part of the Api Key. */
    @JsonProperty("PrivatePart")
    private String privatePart;

    /** Boolean related to the validity of the format. */
    @JsonProperty("IsValidFormat")
    private boolean isValidFormat;

    /**
     * Get the public part of the Api Key
     * @return
     */
    public String getPublicPart() {
        return publicPart;
    }
    /**
     * Set the public part of the Api Key
     * @param value int
     */
    public void setPublicPart(String value) {
        this.publicPart = value;
    }

    /**
     * Get the private part of the Api Key
     * @return
     */
    public String getPrivatePart() {
        return privatePart;
    }
    /**
     * Set the private part of the Api Key
     * @param value int
     */
    public void setPrivatePart(String value) {
        this.privatePart = value;
    }

    /**
     * Get the valid format boolean
     * @return
     */
    public boolean getisValidFormat() {
        return isValidFormat;
    }
    /**
     * Set the valid format boolean
     * @param value int
     */
    public void setisValidFormat(boolean value) {
        this.isValidFormat = value;
    }

}