package com.akapuscinski.logger.domain;

import com.akapuscinski.logger.domain.mapping.StatementMapper;
import com.akapuscinski.logger.domain.mapping.StatementParser;
import com.akapuscinski.logger.domain.responseModels.StatementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StatementService {
    private final StatementRepository statementRepository;
    private final StatementMapper statementMapper;
    private final StatementParser statementParser;

    public StatementService(StatementRepository statementRepository, StatementMapper statementMapper, StatementParser statementParser) {
        this.statementRepository = statementRepository;
        this.statementMapper = statementMapper;
        this.statementParser = statementParser;
    }

    public Page<StatementDTO> findAll(Pageable pageable) {
        return statementRepository.findAll(pageable).map(statementMapper::toDto);
    }

    public List<StatementDTO> parseLogsAndSave(String value) {
        List<StatementDTO> result = new ArrayList<>();
        String[] split = value.split("\n");
        for (String el : split) {
            result.add(statementParser.parse(el));
        }
        return saveAll(result);
    }

    private List<StatementDTO> saveAll(List<StatementDTO> result) {
        return statementRepository.saveAll(result.stream().map(statementMapper::toEntity).collect(Collectors.toList()))
                .stream().map(statementMapper::toDto).collect(Collectors.toList());
    }
}
