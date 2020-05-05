package com.akapuscinski.logger.domain.specification.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangeFilter<FIELD_TYPE extends Comparable<? super FIELD_TYPE>> extends Filter<FIELD_TYPE> {

    private static final long serialVersionUID = 1L;

    private FIELD_TYPE greaterThan;
    private FIELD_TYPE lessThan;
    private FIELD_TYPE greaterOrEqualThan;
    private FIELD_TYPE lessOrEqualThan;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final RangeFilter<?> that = (RangeFilter<?>) o;
        return Objects.equals(greaterThan, that.greaterThan) &&
                Objects.equals(lessThan, that.lessThan) &&
                Objects.equals(greaterOrEqualThan, that.greaterOrEqualThan) &&
                Objects.equals(lessOrEqualThan, that.lessOrEqualThan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), greaterThan, lessThan, greaterOrEqualThan, lessOrEqualThan);
    }

    @Override
    public String toString() {
        return getFilterName() + " ["
                + (getGreaterThan() != null ? "greaterThan=" + getGreaterThan() + ", " : "")
                + (getGreaterOrEqualThan() != null ? "greaterOrEqualThan=" + getGreaterOrEqualThan() + ", " : "")
                + (getLessThan() != null ? "lessThan=" + getLessThan() + ", " : "")
                + (getLessOrEqualThan() != null ? "lessOrEqualThan=" + getLessOrEqualThan() + ", " : "")
                + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
                + (getSpecified() != null ? "specified=" + getSpecified() + ", " : "")
                + (getIn() != null ? "in=" + getIn() : "")
                + "]";
    }
}
