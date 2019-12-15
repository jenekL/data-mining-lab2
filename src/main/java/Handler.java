import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jenya
 */
public class Handler {
    
    private Regression method;
    
    public Handler(){
        method = new Regression();
        readFile();
        method.startLearning();
        System.out.println("\n-----ОБУЧЕНИЕ ЗАВЕРШЕНО!----\n");
        method.testValues();
    }
    
    public void readFile(){
        try {
            File file = new File("data.txt");
            Scanner sc;
            String str;
            String[] arr;
            sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                arr = str.split("\t");
                method.getValues().add(new Value(arr));
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
            File file = new File("test.txt");
            Scanner sc;
            String str;
            String[] arr;
            sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                arr = str.split("\t");
                method.getTestValues().add(new Value(arr));
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
