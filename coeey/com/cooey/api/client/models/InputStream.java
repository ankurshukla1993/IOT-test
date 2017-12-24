package com.cooey.api.client.models;

import java.util.Objects;

public class InputStream {
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[0]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InputStream {\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
