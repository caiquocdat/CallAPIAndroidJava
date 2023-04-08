package com.example.hocapi.model;

public class Currence {
    private boolean success;
    private String terms;
    private String privacy;
    private float timestamp;
    private String source;
    private quotes quotes;

    public Currence(boolean success, String terms, String privacy, float timestamp, String source, com.example.hocapi.model.quotes quotes) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.timestamp = timestamp;
        this.source = source;
        this.quotes = quotes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public com.example.hocapi.model.quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(com.example.hocapi.model.quotes quotes) {
        this.quotes = quotes;
    }
}
