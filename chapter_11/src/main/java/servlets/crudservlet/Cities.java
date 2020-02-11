package servlets.crudservlet;

public enum Cities {
    Moscow,
    Peterburg,
    Kazan,
    Tver;

    public static String[] getNames() {
        return Cities.names();
    }

    private static String[] names() {
        Cities[] cities = Cities.values();
        String[] names = new String[cities.length];

        for (int i = 0; i < cities.length; i++) {
            names[i] = cities[i].name();
        }
        return names;
    }
}
