package com.akapuscinski.logger.domain;

import com.akapuscinski.logger.domain.models.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface StatementRepository extends JpaRepository<Statement, Long>, JpaSpecificationExecutor<Statement> {
}
