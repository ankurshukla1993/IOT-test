package com.facebook.systrace;

public final class SystraceMessage {
    private static final Builder NOOP_BUILDER = new NoopBuilder();

    public static abstract class Builder {
        public abstract Builder arg(String str, double d);

        public abstract Builder arg(String str, int i);

        public abstract Builder arg(String str, long j);

        public abstract Builder arg(String str, Object obj);

        public abstract void flush();
    }

    private interface Flusher {
        void flush(StringBuilder stringBuilder);
    }

    private static class NoopBuilder extends Builder {
        private NoopBuilder() {
        }

        public void flush() {
        }

        public Builder arg(String key, Object value) {
            return this;
        }

        public Builder arg(String key, int value) {
            return this;
        }

        public Builder arg(String key, long value) {
            return this;
        }

        public Builder arg(String key, double value) {
            return this;
        }
    }

    public static Builder beginSection(long tag, String sectionName) {
        return NOOP_BUILDER;
    }

    public static Builder endSection(long tag) {
        return NOOP_BUILDER;
    }
}
