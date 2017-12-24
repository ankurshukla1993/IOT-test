package com.facebook.stetho.dumpapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArgsHelper {
    public static String nextOptionalArg(Iterator<String> argsIter, String defaultValue) {
        return argsIter.hasNext() ? (String) argsIter.next() : defaultValue;
    }

    public static String nextArg(Iterator<String> argsIter, String errorIfMissing) throws DumpUsageException {
        if (argsIter.hasNext()) {
            return (String) argsIter.next();
        }
        throw new DumpUsageException(errorIfMissing);
    }

    public static String[] drainToArray(Iterator<String> argsIter) {
        List<String> args = new ArrayList();
        while (argsIter.hasNext()) {
            args.add(argsIter.next());
        }
        return (String[]) args.toArray(new String[args.size()]);
    }
}
