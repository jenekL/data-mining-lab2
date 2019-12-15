import java.util.ArrayList;

/**
 *
 * @author Jenya
 */
public class Polinom {
    private Mask mask;
    private ArrayList<Double> coef;
    private double SKO = 0;

    public Polinom() {
        coef = new ArrayList<Double>();
    }

    public Polinom(Mask mask, double[] Coef) {
        this.mask = mask;
        coef = new ArrayList<Double>();
        for (int i = 0; i < Coef.length; i++) {
            coef.add(Coef[i]);
        }
    }

    public String printPolinom() {
        int indexCoef = 1;
        StringBuilder str = new StringBuilder();
        str.append("y = ");
        str.append(coef.get(0));

        for (int i = 1; i < Regression.countFields; i++) {
            if (mask.getMask().get(i) == 1) {
                str.append(" + ");
                str.append(coef.get(indexCoef));
                str.append("x");
                str.append(i);
                indexCoef++;
            }

        }
        return str.toString();
    }

    public Mask getMask() {
        return mask;
    }

    public void setMask(Mask mask) {
        this.mask = mask;
    }

    public ArrayList<Double> getCoef() {
        return coef;
    }

    public void setCoef(ArrayList<Double> coef) {
        this.coef = coef;
    }

    public double getSKO() {
        return SKO;
    }

    public void setSKO(double SKO) {
        this.SKO = SKO;
    }

    public void iteratorSKO(double sko) {
        this.SKO += sko;
    }

    public void sqrSKO(int N) {

        SKO = Math.pow(SKO / N, 0.5);
    }
}
