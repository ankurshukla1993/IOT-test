package com.facebook.common.file;

import java.io.File;

public class FileTree {
    public static void walkFileTree(File directory, FileTreeVisitor visitor) {
        visitor.preVisitDirectory(directory);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    walkFileTree(file, visitor);
                } else {
                    visitor.visitFile(file);
                }
            }
        }
        visitor.postVisitDirectory(directory);
    }

    public static boolean deleteContents(File directory) {
        File[] files = directory.listFiles();
        boolean success = true;
        if (files != null) {
            for (File file : files) {
                success &= deleteRecursively(file);
            }
        }
        return success;
    }

    public static boolean deleteRecursively(File file) {
        if (file.isDirectory()) {
            deleteContents(file);
        }
        return file.delete();
    }
}
