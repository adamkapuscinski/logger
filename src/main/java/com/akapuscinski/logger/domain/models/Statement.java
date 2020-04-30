package com.akapuscinski.logger.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "statement")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Statement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "event_date")
    private LocalDateTime eventDate = LocalDateTime.now();

    @Column(name = "data")
    private String data;

    @Column(name = "logging_level")
    private String loggingLevel;

    @Column(name = "calling_class")
    private String callingClass;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Statement statement = (Statement) o;
        if (statement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
