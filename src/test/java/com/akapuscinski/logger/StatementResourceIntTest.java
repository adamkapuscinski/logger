package com.akapuscinski.logger;

import com.akapuscinski.logger.domain.StatementRepository;
import com.akapuscinski.logger.domain.StatementService;
import com.akapuscinski.logger.domain.models.Statement;
import com.akapuscinski.logger.domain.responseModels.StatementDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoggerApplication.class)
public class StatementResourceIntTest {

    private static final String DEFAULT_LOG = "2020-05-05 21:52:09.785  INFO 18344 --- [           main] c.akapuscinski.logger.LoggerApplication  : Message";
    private static final String DEFAULT_LOGGING_DATE = "AAAAAAAA";
    private static final String UPDATED_LOGGING_DATE = "Message";
    private static final String DEFAULT_LOGGING_LEVEL = "AAAAAAAA";
    private static final String UPDATED_LOGGING_LEVEL = "INFO";
    private static final String DEFAULT_CALLING_CLASS = "AAAAAAAAA";
    private static final Long DEFAULT_ID = 1234l;
    private static final String UPDATED_CALLING_CLASS = "c.akapuscinski.logger.LoggerApplication ";
    private static final LocalDateTime DEFAULT_EVENT_DATE = LocalDateTime.now();
    private static final LocalDateTime UPDATED_EVENT_DATE = LocalDateTime.now();

    @Autowired
    private StatementService statementService;
    @Autowired
    private StatementRepository statementRepository;

    public static Statement createEntity() {
        Statement statement = new Statement();
        statement.setId(DEFAULT_ID);
        statement.setData(DEFAULT_LOGGING_DATE);
        statement.setCallingClass(DEFAULT_CALLING_CLASS);
        statement.setLoggingLevel(DEFAULT_LOGGING_LEVEL);
        statement.setEventDate(DEFAULT_EVENT_DATE);
        return statement;
    }

    @Test
    @Transactional
    public void createStatement() {
        long databaseSizeBeforeCreate = statementRepository.findAll().size();

        List<StatementDTO> statementDTOs = statementService.parseLogsAndSave(DEFAULT_LOG);

        Optional<StatementDTO> optStatement = statementDTOs.stream().findFirst();
        assertThat(optStatement.isPresent()).isEqualTo(true);
        assertThat(optStatement.map(StatementDTO::getData).orElse("")).isEqualTo(UPDATED_LOGGING_DATE);
        assertThat(optStatement.map(StatementDTO::getLoggingLevel).orElse("")).isEqualTo(UPDATED_LOGGING_LEVEL);
        assertThat(optStatement.map(StatementDTO::getCallingClass).orElse("")).isEqualTo(UPDATED_CALLING_CLASS);

        long databaseSizeAfterCreate = statementRepository.findAll().size();

        assertThat(databaseSizeBeforeCreate + 1).isEqualTo(databaseSizeAfterCreate);
    }
}
