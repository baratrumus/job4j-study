package multithread.wget;

import java.util.ArrayList;
import java.util.List;


public class Params {
    int speedLimit;
    private List<String> urls;
    private String[] args;

    public Params(String[] args) {
        this.urls = new ArrayList<>();
        this.args = args;
        parser();
    }

    public List<String> urls() {
        return urls;
    }

    public int speedLimit() {
        return speedLimit;
    }


    private void parser() {
        speedLimit = Integer.parseInt(args[0]);
        for (int i = 1; i < args.length; i++) {
            this.urls.add(args[i]);
        }
    }
}
