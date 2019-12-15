import java.util.ArrayList;

/**
 *
 * @author Jenya
 */
public class Mask {
    private ArrayList<Integer> mask;
    private int level;

    public Mask() {
        mask = new ArrayList<Integer>(10);
        mask.add(1);
        for (int i = 0; i < 9; i++) {
            mask.add(0);
        }
    }

    public String printMask() {
        StringBuilder str = new StringBuilder();
        str.append(mask.get(0));
        str.append(" ");
        str.append(mask.get(1));
        str.append(mask.get(2));
        str.append(mask.get(3));
        str.append(mask.get(4));
        str.append(" ");
        str.append(mask.get(5));
        str.append(mask.get(6));
        str.append(mask.get(7));
        str.append(mask.get(8));
        str.append(" ");
        str.append(mask.get(9));

        return str.toString();
    }

    public void createMask(int number) {

    }

    public ArrayList<Integer> getMask() {
        return mask;
    }

    public void setMask(ArrayList<Integer> mask) {
        this.mask = mask;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        level = -1;
        for (Integer i : mask) {
            if (i == 1) {
                level++;
            }
        }
    }
}
