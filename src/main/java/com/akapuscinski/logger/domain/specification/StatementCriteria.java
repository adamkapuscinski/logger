package com.akapuscinski.logger.domain.specification;

import com.akapuscinski.logger.domain.specification.filters.LocalDateTimeFilter;
import com.akapuscinski.logger.domain.specification.filters.LongFilter;
import com.akapuscinski.logger.domain.specification.filters.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementCriteria {
    private LongFilter id;
    private StringFilter data;
    private StringFilter loggingLevel;
    private StringFilter callingClass;
    private LocalDateTimeFilter eventDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StatementCriteria that = (StatementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(data, that.data) &&
            Objects.equals(loggingLevel, that.loggingLevel) &&
            Objects.equals(callingClass, that.callingClass) &&
            Objects.equals(eventDate, that.eventDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, loggingLevel, callingClass, eventDate);
    }

    @Override
    public String toString() {
        return
            (id != null ? "id=" + id + ", " : "") +
            (data != null ? "data=" + data + ", " : "") +
            (loggingLevel != null ? "loggingLevel=" + loggingLevel + ", " : "") +
            (callingClass != null ? "callingClass=" + callingClass + ", " : "") +
            (eventDate != null ? "eventDate=" + eventDate + " " : "");
    }
}
