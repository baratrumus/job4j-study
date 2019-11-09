
public class DrawField {

    public void draw(int size, String[][] table) {
        StringBuilder sb = new StringBuilder();
        for (int vert = 0; vert < size; vert++) {
            for (int hor = 0; hor < size; hor++) {
                sb.append(hor);
                sb.append(" ");
            }
        }
    }
}
