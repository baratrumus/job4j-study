package ru.job4j.io.consolechat;

public class SimInput implements Input {

    String[] inputArr;
    int count = 0;

    public SimInput(String[] inputArr) {
        this.inputArr = inputArr;
    }

    @Override
    public String ask() {
        String result = "";
        if (count < inputArr.length) {
            result = inputArr[count++];
        }
        return result;
    }

}
