package ru.job4j.io.files;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Ключи
 *  *    -d - директория в которая начинать поиск.
 *  *    -n - имя файл, маска, либо регулярное выражение.
 *  *    -m - искать по маске, либо -f - полное совпадение имени.
 *  *    -o - результат записать в файл.
 */
public class Args {
    private String directory;
    private String filename;
    private String searchMaskOrFullName;
    private String outputFile;
    private String regExp;
    private String[] args;


    public Args(String[] args) throws org.apache.commons.cli.ParseException {
        this.args = args;
        parser();
    }

    public String directory() {
        return directory;
    }

    public String filename() {
        return filename;
    }

    public String regExp() {
        return regExp;
    }

    public String searchMaskOrFullName() {
        return searchMaskOrFullName;
    }

    public String output() {
        return outputFile;
    }


    private void parser() throws org.apache.commons.cli.ParseException {
        Options options = new Options();
        options.addOption(new Option("d",  true, "directory"));
        options.addOption(new Option("n",  true, "file"));
        options.addOption(new Option("o",  true, "outputFile"));
        Option maskOption = new Option("m",  false, "mask");
        Option fileOption = new Option("f",  false, "fileFullName");
        Option regExpOption = new Option("r",  false, "Reg Exp");

        maskOption.setArgs(0);
        fileOption.setArgs(0);
        regExpOption.setArgs(0);
        options.addOption(maskOption);
        options.addOption(fileOption);
        options.addOption(regExpOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, this.args);

        if (cmd.hasOption("d")) {
            String[] arguments = cmd.getOptionValues("d");
            this.directory = arguments[0];
        }
        if (cmd.hasOption("n")) {
            String[] arguments = cmd.getOptionValues("n");
            this.filename = arguments[0];
        }
        if (cmd.hasOption("r")) {
            this.regExp = "r";
        }
        if (cmd.hasOption("m")) {
            this.searchMaskOrFullName = "m";
        }
        if (cmd.hasOption("f")) {
            this.searchMaskOrFullName = "f";
        }
        if (cmd.hasOption("o")) {
            String[] arguments = cmd.getOptionValues("o");
            this.outputFile = arguments[0];
        }
    }
}
