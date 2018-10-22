package serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MergeData {

    @JsonProperty("Field")
    private String field;

    @JsonProperty("Value")
    private String value;

    public MergeData(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
