package com.akapuscinski.logger.Presentation;

import com.akapuscinski.logger.domain.StatementService;
import com.akapuscinski.logger.domain.responseModels.StatementDTO;
import com.akapuscinski.logger.domain.specification.StatementCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/logs")
public class StatementResource {
    @Autowired
    private StatementService statementService;
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<StatementDTO>> getStatements(Pageable pageable, StatementCriteria criteria) {
        return ResponseEntity.ok().body(statementService.findAll(criteria, pageable));
    }

    /**
     * @param value is a log files sent via body in PostRequest.
     * Every line becomes separated by \n characted and stored in DB if was properly interpreted.
     * @return List of StatementDTO objects - elements that were persisted to DB
     */
    @PostMapping()
    public ResponseEntity<List<StatementDTO>> getStatements(@RequestBody String value) {
        return ResponseEntity.ok().body(statementService.parseLogsAndSave(value));
    }

}
