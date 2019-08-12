package ru.job4j.io;

import org.apache.commons.cli.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Args {
    private String directory;
    private String output;
    private List<String> exclude;
    private String[] args;

    public Args(String[] args) throws org.apache.commons.cli.ParseException {
        this.args = args;
        exclude = new ArrayList<>();
        parser();
    }

    public String directory() {
        return directory;
    }

    public List<String> exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }


    private void parser() throws org.apache.commons.cli.ParseException {
        Options options = new Options();
        options.addOption(new Option("d",  true, "directory"));
        options.addOption(new Option("o",  true, "output"));
        Option optionEx = new Option("e",  true, "exclude");
        optionEx.setArgs(2);
        options.addOption(optionEx);
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = parser.parse(options, this.args);


        if (cmd.hasOption("d")) {
            String[] arguments = cmd.getOptionValues("d");
            this.directory = arguments[0];
        }
        if (cmd.hasOption("e")) {
            String[] arguments = cmd.getOptionValues("e");
            for (int i = 0; i < arguments.length; i++) {
                this.exclude.add(arguments[i]);
            }
        }
        if (cmd.hasOption("o")) {
            String[] arguments = cmd.getOptionValues("o");
            this.output = arguments[0];
        }
    }
}
