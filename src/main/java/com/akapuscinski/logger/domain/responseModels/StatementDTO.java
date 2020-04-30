package com.akapuscinski.logger.domain.responseModels;

import com.akapuscinski.logger.domain.models.Statement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementDTO {
    private Long id;
    private LocalDateTime eventDate = LocalDateTime.now();
    private String data;
    private String loggingLevel;
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
