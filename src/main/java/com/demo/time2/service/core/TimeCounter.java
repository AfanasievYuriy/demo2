package com.demo.time2.service.core;

import com.demo.time2.domain.ActionType;
import com.demo.time2.domain.ResultDuration;
import com.demo.time2.domain.TimePair;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TimeCounter {

    /**
     * Parses stack and summing time value.
     * @param allPairsList
     * @return time value for a file
     */
    public ResultDuration getTimeDuration(List<TimePair> allPairsList) {
        Deque<TimePair> stack = validatePairsList(allPairsList);
        long resultMinutes = 0;
        boolean withErrors = false;

        if (stack.size() != allPairsList.size()) {
            withErrors = true;
        }

        while (!stack.isEmpty()) {
            LocalTime out = stack.pop().getTime();
            LocalTime in = stack.pop().getTime();
            resultMinutes += Duration.between(in, out).toMinutes();
        }

        return new ResultDuration(LocalTime.MIN.plusMinutes(resultMinutes), withErrors);
    }

    /**
     * Validates list of {@link TimePair}. For example we have list of {@link TimePair},
     * all TimePairs is valid:
     * 1) вход,09:00
     * 2) выход,10:00
     * 3) выход,11:00
     * 4) вход,12:00
     * 5) вход,13:00
     * 6) выход,17:00
     * Algo searches вход-выход pairs only. If there is a "вход" and no "выход", method skip this
     * {@link TimePair}.
     * @param allPairsList
     * @return stack of {@link TimePair}, where there are no "вход" or "выход" values that folows
     * next to each other
     */
    Deque<TimePair> validatePairsList(List<TimePair> allPairsList) {
        Deque<TimePair> stack = new LinkedList<>();

        stack.push(allPairsList.get(0));
        for (int i = 1; i < allPairsList.size(); i++) {

            boolean checkIfNextIn = (i == allPairsList.size() - 1
                || allPairsList.get(i + 1).getAction() != ActionType.IN);
            boolean checkIfOutOnStack = stack.peek().getAction() == ActionType.OUT;
            boolean checkIfInOnStack = stack.peek().getAction() == ActionType.IN;

            if (allPairsList.get(i).getAction() == ActionType.OUT
                && checkIfInOnStack) {
                stack.push(allPairsList.get(i));
            }
            else if (allPairsList.get(i).getAction() == ActionType.IN
                && checkIfOutOnStack
                && checkIfNextIn) {
                stack.push(allPairsList.get(i));
            }
        }
        return stack;
    }

}
