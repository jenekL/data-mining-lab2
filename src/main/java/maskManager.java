import java.util.ArrayList;

/**
 *
 * @author Jenya
 */
public class maskManager {
    private ArrayList<Mask> masks;

    public maskManager() {
        masks = new ArrayList<Mask>();
        String binaryString;
        int counter;
        int countMasks = (int) (Math.pow(2, 9) - 1);
        for (int i = 1; i <= countMasks; i++) {
            binaryString = Integer.toBinaryString(i);
            masks.add(new Mask());
            counter = 1;
            for (int j = binaryString.length() - 1; j >= 0; j--) {
                masks.get(i - 1).getMask().set(counter, Character.getNumericValue(binaryString.charAt(j)));

                counter++;
            }
            masks.get(i - 1).setLevel();
        }
    }

    public ArrayList<Mask> getLevel(int level) {
        ArrayList<Mask> levelArray;
        levelArray = new ArrayList<Mask>();
        for (Mask i : masks) {
            if (i.getLevel() == level) {
                levelArray.add(i);
            }
        }
        return levelArray;
    }

    public ArrayList<Mask> getMasks() {
        return masks;
    }

    public void setMasks(ArrayList<Mask> masks) {
        this.masks = masks;
    }


}
