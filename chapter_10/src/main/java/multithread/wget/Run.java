package multithread.wget;

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
                args = new String[] {"200000",
                        "http://baticworld.ru/images/Gallery_images//P1050210_m.jpg",
                        "http://baticworld.ru/images/Gallery_images//P1050310_m.jpg"
                        };
            }
            Params params = new Params(args);
            for (String urlSrting : params.urls()) {
                URL url = new URL(urlSrting);
                Wget wg = new Wget(params.speedLimit, url);
                Thread run = new Thread(wg);
                run.start();
               // run.join(); // следующий поток начнется только когда предыдущий закончит работу
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
