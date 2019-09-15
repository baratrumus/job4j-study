package ru.job4j.io.cmd;

import java.io.File;

/*
 * Please solve the followig puzzle which simulates generic directory structures.
 * The solution should be directory agnostic.
 * Be succinct yet readable. You should not exceed more than 200 lines.
 * Consider adding comments and asserts to help the understading.
 * Code can be compiled with javac Directory.java
 * Code should be executed as: java -ea Directory (-ea option it's to enabled the assert)
 */

/**
 * pathtest относительный путь в формате линукс для прохождения теста
 */
public class Shell {
    private final String separator = File.separator;
    private final String directory = System.getProperty("java.io.tmpdir") + "Directory";
    private int directoryLength = directory.length();
    private File directoryFile = new File(directory);
    private boolean created = directoryFile.mkdir();
    private String pathTest = "/";
    private String fullPath = directory;

    Shell cd(final String pathGot) {
        if ("/".equals(pathGot)) {
           goRoot();
        } else if (pathGot.contains("//")) {
            goUp();
            String dir = pathGot.replaceAll("/", "");
            this.pathTest += dir;
            this.fullPath += separator + dir;
        } else if (pathGot.contains("/")) {
            String[] pathArr = pathGot.split("/");
            for (String str : pathArr) {
                if ("..".equals(str)) {
                   goUp();
                } else if (!".".equals(str)) {
                   goIn(str);
                }
            }
        } else {
            if ("..".equals(pathGot)) {
                goUp();
            } else {
                goIn(pathGot);
            }
        }
        return this;
    }

    private void goIn(String str) {
        if ("/".equals(pathTest)) {
            this.pathTest += str;
            this.fullPath += separator + str;
        } else if (!str.isEmpty()) {
            this.pathTest += "/" + str;
            this.fullPath += separator + str;
        }
    }

    private void goUp() {
        File tmpFile = new File(this.fullPath);
        this.fullPath = tmpFile.getParentFile().getAbsolutePath();
        if (this.fullPath.length() != directoryLength) {
            this.pathTest = "/" + this.fullPath.substring(directoryLength + 1);
        } else {
            this.pathTest = "/";
        }
    }

    private void goRoot() {
        this.pathTest = "/";
        this.fullPath = directory + separator;
    }

    public String path() {
        //String rt = this.path.replaceAll("\\\\", "\\");
        return this.pathTest;
    }

    public String getDirectory() {
        return this.directory;
    }

}
