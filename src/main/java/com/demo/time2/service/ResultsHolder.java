package com.demo.time2.service;

import com.demo.time2.DTO.ResultDurationDTO;
import com.demo.time2.domain.ResultDuration;
import com.demo.time2.domain.TimePair;
import com.demo.time2.service.core.FileParser;
import com.demo.time2.service.core.TimeCounter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

@Component
public class ResultsHolder {
    private static final String RESOURCE_DIR_PATH = FilenameUtils
        .separatorsToSystem("/home/yuriy/IdeaProjects/time2/src/main/resources/");
    private static final String pattern =
        "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)(.csv)*";
    public static final Pattern fileNamePattern = Pattern.compile(pattern);

    private Map<String, ResultDuration> resultMap;

    public ResultsHolder() {
        resultMap = new HashMap<>();
    }

    public ResultDuration getResultDurationByDate(String date) {
        ResultDuration resultDuration = resultMap.get(date);
        if (resultDuration == null) throw new InvalidPathException(date + ".csv", "no such file");
        return resultDuration;
    }

    public List<ResultDurationDTO> getAllResultDurations() {
        List<ResultDurationDTO> list = resultMap.entrySet().stream()
            .map(e -> new ResultDurationDTO(e.getValue(), e.getKey()))
            .collect(Collectors.toList());
        return list;
    }

    public void initHolder() throws IOException {
        List<Path> filePathes = Files
            .list(Paths.get(RESOURCE_DIR_PATH))
            .filter(p -> p.toString().endsWith(".csv"))
            .filter(p -> Files.isRegularFile(p))
            .filter(p -> fileNamePattern.matcher(p.getFileName().toString()).matches())
            .collect(Collectors.toList());

        for (Path filePath : filePathes) {
            List<TimePair> collect = new FileParser().parse(filePath);
            ResultDuration result = new TimeCounter().getTimeDuration(collect);
            resultMap.put(FilenameUtils.removeExtension(filePath.getFileName().toString()), result);
        }
    }
}
