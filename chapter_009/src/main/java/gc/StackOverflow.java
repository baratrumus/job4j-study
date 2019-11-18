package gc;


/**
 *  Смысл программы в выбросе StackOverflowException из за рекурсии
 */
public class StackOverflow {
    private static Runtime rt = Runtime.getRuntime();

    public static class User {
        public String name;
        public User(String name) {
            this.name = name;
        }
    }

    public static void info(int cnt) {

        int kb = 1024;
        long avgSize = (rt.totalMemory() - rt.freeMemory()) / cnt;
        System.out.println("Average user size[Bait] " + avgSize);

        for (int i = 0; i < 10000; i++) {
            User user = new User("User " + i);
            info(i + 1);
        }
    }

    public static void main(String[] args) {
        info(1);
    }
}
