package org.webrtc;

public class StatsReport {
    public final String id;
    public final double timestamp;
    public final String type;
    public final Value[] values;

    public static class Value {
        public final String name;
        public final String value;

        public Value(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("[").append(this.name).append(": ").append(this.value).append("]");
            return builder.toString();
        }
    }

    public StatsReport(String id, String type, double timestamp, Value[] values) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.values = values;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id: ").append(this.id).append(", type: ").append(this.type).append(", timestamp: ").append(this.timestamp).append(", values: ");
        for (Value value : this.values) {
            builder.append(value.toString()).append(", ");
        }
        return builder.toString();
    }
}
