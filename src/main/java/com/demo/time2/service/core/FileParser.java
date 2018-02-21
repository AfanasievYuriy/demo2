package com.demo.time2.service.core;

import com.demo.time2.domain.ActionType;
import com.demo.time2.domain.TimePair;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parses file with information about entering (leaving) the building.
 */
public class FileParser {
    private final static String pattern = ".*(вход\\b|выход\\b).+(([0-1][0-9]|2[0-3]):[0-5][0-9]).*";
    private final static Pattern fileLinePattern = Pattern.compile(pattern);

    public List<TimePair> parse(Path pathToFile) throws IOException {
        Stream<String> stream = Files.lines(pathToFile);
        List<TimePair> pairsFromFile = getValidTimePairs(stream);
        pairsFromFile = submitBoundaries(pairsFromFile);

        return pairsFromFile;
    }

    /**
     * Add {@link TimePair} to list, when the first value in list is OUT(выход). For example:
     * if list starts with (OUT, 06:00) that mean that worker started his day in the office, so
     * method add (IN, 00:00) to the top of the list
     * Second option - to add (OUT, 23:59) to the end of the list, if list ends with IN. (that
     * means that worker ended his day in the office)
     * @param pairs
     * @return
     */
    List<TimePair> submitBoundaries(List<TimePair> pairs) {
        if (pairs.get(pairs.size() - 1).getAction().equals(ActionType.IN)) {
            pairs.add(new TimePair(ActionType.OUT, LocalTime.MAX));
        }
        if (pairs.get(0).getAction().equals(ActionType.OUT)) {
            pairs.add(0, new TimePair(ActionType.IN, LocalTime.MIN));
        }
        return new ArrayList<>(pairs);
    }

    /**
     * Validates every line in .csv file
     * @param pairsStream stream of .csv file lines
     * @return list of valid {@link TimePair} where every timePair represents line in .csv file
     */
    List<TimePair> getValidTimePairs(Stream<String> pairsStream) {
        return pairsStream
                .map(s -> {
                    Matcher matcher = fileLinePattern.matcher(s);
                    Optional<TimePair> pair = matcher.find()
                        ? Optional.of(
                        new TimePair(ActionType.parseActionType(matcher.group(1)),
                            LocalTime.parse(matcher.group(2))))
                        : Optional.empty();
                    return pair;
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
