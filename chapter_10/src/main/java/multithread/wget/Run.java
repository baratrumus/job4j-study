package multithread.wget;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 *
 * */
public class Run {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                args = new String[] {"", ""};
            }
            Params pr = getParamsFromArgs(args);
            Wget wg = new Wget(pr);
            Thread run = new Thread(wg);
            run.start();
            run.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static Params getParamsFromArgs(String[] args) throws Exception {
        String urlSrting = "http://baticworld.ru/images/Gallery_images//P1050310_m.jpg";
        URL url = new URL(urlSrting);
       // URL url = new URL(args[0]);
        int speedLimit = 200000;
        if (args[1] != null && !args[1].isBlank()) {
            speedLimit = Integer.parseInt(args[1]);
        }
        return new Params(url, speedLimit);
    }
}
