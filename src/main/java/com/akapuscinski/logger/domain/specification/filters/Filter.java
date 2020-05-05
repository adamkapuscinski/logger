package com.akapuscinski.logger.domain.specification.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter<FIELD_TYPE> implements Serializable {

    private static final long serialVersionUID = 1L;
    private FIELD_TYPE equals;
    private Boolean specified;
    private List<FIELD_TYPE> in;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Filter<?> filter = (Filter<?>) o;
        return Objects.equals(equals, filter.equals) &&
                Objects.equals(specified, filter.specified) &&
                Objects.equals(in, filter.in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equals, specified, in);
    }

    @Override
    public String toString() {
        return getFilterName() + " ["
                + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
                + (getIn() != null ? "in=" + getIn() + ", " : "")
                + (getSpecified() != null ? "specified=" + getSpecified() : "")
                + "]";
    }

    protected String getFilterName() {
        return this.getClass().getSimpleName();
    }
}
