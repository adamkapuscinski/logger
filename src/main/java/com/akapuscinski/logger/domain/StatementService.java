package com.akapuscinski.logger.domain;

import com.akapuscinski.logger.domain.mapping.StatementMapper;
import com.akapuscinski.logger.domain.mapping.StatementParser;
import com.akapuscinski.logger.domain.models.Statement;
import com.akapuscinski.logger.domain.models.Statement_;
import com.akapuscinski.logger.domain.responseModels.StatementDTO;
import com.akapuscinski.logger.domain.specification.StatementCriteria;
import com.akapuscinski.logger.domain.specification.logic.QueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StatementService extends QueryService<Statement> {
    private final StatementRepository statementRepository;
    private final StatementMapper statementMapper;
    private final StatementParser statementParser;

    public StatementService(StatementRepository statementRepository, StatementMapper statementMapper, StatementParser statementParser) {
        this.statementRepository = statementRepository;
        this.statementMapper = statementMapper;
        this.statementParser = statementParser;
    }

    public Page<StatementDTO> findAll(StatementCriteria criteria, Pageable pageable) {
        final Specification<Statement> specification = createSpecification(criteria);
        return statementRepository.findAll(specification, pageable).map(statementMapper::toDto);
    }

    private Specification<Statement> createSpecification(StatementCriteria criteria) {
        Specification<Statement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Statement_.id));
            }
            if (criteria.getCallingClass() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCallingClass(), Statement_.callingClass));
            }
            if (criteria.getData() != null) {
                specification = specification.and(buildStringSpecification(criteria.getData(), Statement_.data));
            }
            if (criteria.getLoggingLevel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLoggingLevel(), Statement_.loggingLevel));
            }
            if (criteria.getEventDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventDate(), Statement_.eventDate));
            }
        }
        return specification;
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
    public StatementDTO save(Statement statement) {
        return statementMapper.toDto(statementRepository.save(statement));
    }
}
