package com.cooey.api;

import humanize.util.Constants;
import java.util.Arrays;
import java.util.List;

public class CollectionFormats {

    public static class CSVParams {
        protected List<String> params;

        public CSVParams(List<String> params) {
            this.params = params;
        }

        public CSVParams(String... params) {
            this.params = Arrays.asList(params);
        }

        public List<String> getParams() {
            return this.params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }

        public String toString() {
            return StringUtil.join((String[]) this.params.toArray(new String[0]), ",");
        }
    }

    public static class PIPESParams extends CSVParams {
        public PIPESParams(List<String> params) {
            super((List) params);
        }

        public PIPESParams(String... params) {
            super(params);
        }

        public String toString() {
            return StringUtil.join((String[]) this.params.toArray(new String[0]), "|");
        }
    }

    public static class SSVParams extends CSVParams {
        public SSVParams(List<String> params) {
            super((List) params);
        }

        public SSVParams(String... params) {
            super(params);
        }

        public String toString() {
            return StringUtil.join((String[]) this.params.toArray(new String[0]), Constants.SPACE);
        }
    }

    public static class TSVParams extends CSVParams {
        public TSVParams(List<String> params) {
            super((List) params);
        }

        public TSVParams(String... params) {
            super(params);
        }

        public String toString() {
            return StringUtil.join((String[]) this.params.toArray(new String[0]), "\t");
        }
    }
}
