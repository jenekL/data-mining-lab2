import java.util.ArrayList;

/**
 *
 * @author Jenya
 */
public class Mask {
    private ArrayList<Integer> mask;
    private int level;

    public Mask(int n) {
        mask = new ArrayList<Integer>(n);
        mask.add(1);
        for (int i = 0; i < n - 1; i++) {
            mask.add(0);
        }
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
