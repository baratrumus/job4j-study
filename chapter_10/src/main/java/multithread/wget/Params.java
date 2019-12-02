package multithread.wget;

import java.net.URL;

public class Params {
    URL url;
    int speedLimit;

    public Params(URL url, int sl) {
        this.url = url;
        this.speedLimit = sl;
    }
}
