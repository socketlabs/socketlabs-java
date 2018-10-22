package com.socketLabs.serialization;

import java.util.List;

class MergeData {

    private List<List<MergeField>> perMessage;

    private List<MergeField> global;

    public MergeData(List<List<MergeField>> perMessage, List<MergeField> global) {
        this.perMessage = perMessage;
        this.global = global;
    }

    public List<List<MergeField>> getPerMessage() {
        return perMessage;
    }
    public void setPerMessage(List<List<MergeField>> perMessage) {
        this.perMessage = perMessage;
    }

    public List<MergeField> getGlobal() {
        return global;
    }
    public void setGlobal(List<MergeField> global) {
        this.global = global;
    }
}
