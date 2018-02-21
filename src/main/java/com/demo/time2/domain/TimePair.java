package com.demo.time2.domain;

import java.time.LocalTime;

/**
 * Class represents pair of action and time values. Time = time, when worker entered (or leaved)
 * a building
 */
public class TimePair {
    private ActionType action;
    private LocalTime time;

    public TimePair(ActionType action, LocalTime time) {
        this.action = action;
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public ActionType getAction() {
        return action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimePair timePair = (TimePair) o;

        if (!time.equals(timePair.time)) {
            return false;
        }
        return action.equals(timePair.action);
    }

    @Override
    public int hashCode() {
        int result = time.hashCode();
        result = 31 * result + action.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "domain.TimePair{" +
            "action='" + action + '\'' +
            ", time=" + time +
            '}';
    }
}
