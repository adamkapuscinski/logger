package com.akapuscinski.logger.domain.specification.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringFilter extends Filter<String> {

    private static final long serialVersionUID = 1L;

    private String contains;

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
        final StringFilter that = (StringFilter) o;
        return Objects.equals(contains, that.contains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contains);
    }

    @Override
    public String toString() {
        return getFilterName() + " ["
                + (getContains() != null ? "contains=" + getContains() + ", " : "")
                + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
                + (getSpecified() != null ? "specified=" + getSpecified() : "")
                + "]";
    }

}
