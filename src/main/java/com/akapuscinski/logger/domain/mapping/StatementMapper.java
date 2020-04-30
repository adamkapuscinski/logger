package com.akapuscinski.logger.domain.mapping;

import com.akapuscinski.logger.domain.models.Statement;
import com.akapuscinski.logger.domain.responseModels.StatementDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatementMapper extends EntityMapper<StatementDTO, Statement> {

    StatementDTO toDto(Statement statement);

    Statement toEntity(StatementDTO statementDTO);

    default Statement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Statement statement = new Statement();
        statement.setId(id);
        return statement;
    }
}

