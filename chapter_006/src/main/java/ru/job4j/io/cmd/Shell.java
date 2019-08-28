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
public class Shell {
    String separator = File.separator;
    String directory = System.getProperty("java.io.tmpdir") + "Directory";
    int directoryLength = directory.length();
    File directoryFile = new File(directory);
    boolean created = directoryFile.mkdir();
    private String pathTest = "/";
    private String fullPath = directory + separator;

    Shell cd(final String pathGot) {
        if ("/".equals(pathGot)) {
            this.pathTest = "/";
            this.fullPath = directory + separator;
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
                    if ("/".equals(pathTest)) {
                        this.pathTest += str;
                        this.fullPath += str;
                    } else if (!str.isEmpty()) {
                        this.pathTest += "/" + str;
                        this.fullPath += separator + str;
                    }
                }
            }
        } else {
            if ("/".equals(pathTest)) {
                this.pathTest += pathGot;
                this.fullPath += separator + pathGot;
            } else if ("..".equals(pathGot)) {
               goUp();
            } else {
                this.pathTest += "/" + pathGot;
                this.fullPath += separator + pathGot;
            }
        }
        return this;
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

    public String path() {
        //String rt = this.path.replaceAll("\\\\", "\\");
        return this.pathTest;
    }

}
