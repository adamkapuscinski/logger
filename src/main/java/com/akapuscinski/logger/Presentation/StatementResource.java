package com.akapuscinski.logger.Presentation;

import com.akapuscinski.logger.domain.StatementService;
import com.akapuscinski.logger.domain.responseModels.StatementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/logs")
public class StatementResource {
    @Autowired
    private StatementService statementService;
    @GetMapping()
    public ResponseEntity<Page<StatementDTO>> getStatements(Pageable pageable) {
        return ResponseEntity.ok().body(statementService.findAll(pageable));
    }

}
