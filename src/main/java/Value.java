/**
 *
 Specifically:
 1. X1 Relative Compactness
 2. X2 Surface Area
 3. X3 Wall Area
 4. X4 Roof Area
 5. X5 Overall Height
 6. X6 Orientation
 7. X7 Glazing Area
 8. X8 Glazing Area Distribution
 9. y1 Heating Load
 10. y2 Cooling Load
 *
 * @author Jenya
 */
public class Value {
    
    private double x1;
    private double x2;
    private double x3;
    private double x4;
    private double x5;
    private double x6;
    private double x7;
    private double x8;
    private double y1;
    private double y2;

    public Value(String[] arr){
        this.x1 = Double.parseDouble(arr[0].replace(',','.'));
        this.x2 = Double.parseDouble(arr[1].replace(',','.'));
        this.x3 = Double.parseDouble(arr[2].replace(',','.'));
        this.x4 = Double.parseDouble(arr[3].replace(',','.'));
        this.x5 = Double.parseDouble(arr[4].replace(',','.'));
        this.x6 = Double.parseDouble(arr[5].replace(',','.'));
        this.x7 = Double.parseDouble(arr[6].replace(',','.'));
        this.x8 = Double.parseDouble(arr[7].replace(',','.'));
        this.y1 = Double.parseDouble(arr[8].replace(',','.'));
        this.y2 = Double.parseDouble(arr[9].replace(',','.'));
    }
    
    public double getValue(int index){
        switch (index) {
            case (0):
                return x1;
            case (1):
                return x2;
            case (2):
                return x3;
            case (3):
                return x4;
            case (4):
                return x5;
            case (5):
                return x6;
            case (6):
                return x7;
            case (7):
                return x8;
            case (8):
                return y1;
            case (9):
                return y2;
            default:
                return 0;
        }
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getX4() {
        return x4;
    }

    public void setX4(double x4) {
        this.x4 = x4;
    }

    public double getX5() {
        return x5;
    }

    public void setX5(double x5) {
        this.x5 = x5;
    }

    public double getX6() {
        return x6;
    }

    public void setX6(double x6) {
        this.x6 = x6;
    }

    public double getX7() {
        return x7;
    }

    public void setX7(double x7) {
        this.x7 = x7;
    }

    public double getX8() {
        return x8;
    }

    public void setX8(double x8) {
        this.x8 = x8;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }
}
