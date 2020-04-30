package com.akapuscinski.logger.domain;

import com.akapuscinski.logger.domain.mapping.StatementMapper;
import com.akapuscinski.logger.domain.responseModels.StatementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StatementService {
    private final StatementRepository statementRepository;
    private final StatementMapper statementMapper;

    public StatementService(StatementRepository statementRepository,
                            StatementMapper statementMapper) {
        this.statementRepository = statementRepository;
        this.statementMapper = statementMapper;
    }

    public Page<StatementDTO> findAll(Pageable pageable) {
        return statementRepository.findAll(pageable).map(statementMapper::toDto);
    }

    public StatementDTO parseLogsAndSave(String value) {
        StatementDTO statementDTO = new StatementDTO();
        System.out.println(value);
        return statementDTO;
    }
}
