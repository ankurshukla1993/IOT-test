package humanize.util;

import com.google.common.base.Preconditions;
import humanize.time.TimeMillis;

public final class Parameters {

    public static final class PaceParameters {
        public long interval;
        public PluralizeParams plural;

        public static PaceParameters begin(String one) {
            return new PaceParameters().one(one);
        }

        private PaceParameters() {
        }

        public void checkArguments() {
            Preconditions.checkArgument(this.plural != null, "Plural parameters are required");
        }

        public PaceParameters exts(Object... exts) {
            this.plural.exts(exts);
            return this;
        }

        public PaceParameters interval(long interval) {
            this.interval = interval;
            return this;
        }

        public PaceParameters interval(TimeMillis interval) {
            this.interval = interval.millis();
            return this;
        }

        public PaceParameters many(String many) {
            this.plural.many(many);
            return this;
        }

        public PaceParameters none(String none) {
            this.plural.none(none);
            return this;
        }

        public PaceParameters one(String one) {
            this.plural = PluralizeParams.begin(one);
            return this;
        }

        public PaceParameters plural(PluralizeParams plural) {
            this.plural = plural;
            return this;
        }
    }

    public static final class PluralizeParams {
        public Object[] exts;
        public String many;
        public String none;
        public String one;

        public static PluralizeParams begin(String one) {
            return new PluralizeParams().one(one);
        }

        private PluralizeParams() {
        }

        public PluralizeParams exts(Object... exts) {
            this.exts = exts;
            return this;
        }

        public PluralizeParams many(String many) {
            this.many = many;
            return this;
        }

        public PluralizeParams none(String none) {
            this.none = none;
            return this;
        }

        public PluralizeParams one(String one) {
            this.one = one;
            return this;
        }
    }

    public static final class SlugifyParams {
        public boolean isToLowerCase = true;
        public String separator = "-";

        public static SlugifyParams begin() {
            return new SlugifyParams();
        }

        private SlugifyParams() {
        }

        public SlugifyParams separator(String separator) {
            this.separator = separator;
            return this;
        }

        public SlugifyParams toLowerCase(boolean isToLowerCase) {
            this.isToLowerCase = isToLowerCase;
            return this;
        }
    }

    private Parameters() {
    }
}
