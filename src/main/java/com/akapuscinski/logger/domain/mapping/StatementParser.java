package com.akapuscinski.logger.domain.mapping;

import com.akapuscinski.logger.domain.responseModels.StatementDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StatementParser {

    public StatementDTO parse(String value) {
        StatementDTO statementDTO = new StatementDTO();
        try {
            statementDTO.setEventDate(extractDate(value));
            statementDTO.setLoggingLevel(extractLogType(value));
            statementDTO.setData(extractMessage(value));
            statementDTO.setCallingClass(extractCallingClass(value));
        } catch (Error e) {
            e.printStackTrace();
        }
        return statementDTO;
    }

    private LocalDateTime extractDate(String value) {
        Pattern pattern = Pattern.compile("((\\d{4}-\\d{2}-\\d{2}) (\\d{2}:\\d{2}:\\d{2}.\\d{3}))");
        Matcher matcher = pattern.matcher(value);
        while(matcher.find())
        {
            String foundedDate = matcher.group(1);
            value = value.replace(foundedDate, "");
            String[] split = foundedDate.split("\\.");
            if (Objects.nonNull(split[0])) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime ldt = LocalDateTime.parse(split[0], formatter);
                return ldt;
            }
        }
        return null;
    }
    private String extractLogType(String value) {
        Pattern pattern = Pattern.compile("(INFO|WARN|DEBUG|ERROR)");
        Matcher matcher = pattern.matcher(value);
        while(matcher.find())
        {
            return matcher.group(1);
        }
        return null;
    }
    private String extractMessage(String value) {
        Pattern pattern = Pattern.compile("(: .*)");
        Matcher matcher = pattern.matcher(value);
        while(matcher.find())
        {
            return matcher.group(1).substring(2);
        }
        return null;

    }
    private String extractCallingClass(String value) {
        Pattern pattern = Pattern.compile("(\\[.*] .* :)");
        Matcher matcher = pattern.matcher(value);
        while(matcher.find())
        {
            Pattern subPattern = Pattern.compile("(\\[.*]) (.*) (:)");
            Matcher subMatcher = subPattern.matcher(matcher.group(1));
            while (subMatcher.find()) {
                System.out.println(subMatcher.group(2));
                return subMatcher.group(2);
            }
        }
        return null;
    }
}
