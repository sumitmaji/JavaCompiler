package com.sum.frontend;

public enum ParsingStrategy {
    SINGLE_LOOKAHEAD("single"), MULTIPLE_LOOKAHEAD("multiple");

    String type;
    ParsingStrategy(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
