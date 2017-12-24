package io.fabric.sdk.android.services.common;

import java.io.File;
import java.util.Comparator;

class CommonUtils$1 implements Comparator<File> {
    CommonUtils$1() {
    }

    public int compare(File file0, File file1) {
        return (int) (file0.lastModified() - file1.lastModified());
    }
}
