package com.demo.time2.domain;

/**
 * Enum class that represents action value. Can be IN or OUT according to вход and выход
 * possible values.
 */
public enum ActionType {
    IN("вход"),
    OUT("выход");

    private String representation;
    ActionType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public static ActionType parseActionType(String representation) {
        return representation.equals(IN.representation) ? IN : OUT;
    }
}
