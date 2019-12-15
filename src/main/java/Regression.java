import java.util.ArrayList;

/**
 *
 * @author Jenya
 */
public class Regression {

    static int countFields = 10;
    private ArrayList<Value> values;
    private maskManager manager;
    private ArrayList<Polinom> polinoms;
    private ArrayList<Value> testValues;

    public Regression() {
        values = new ArrayList<Value>();
        testValues = new ArrayList<Value>();
        polinoms = new ArrayList<Polinom>();
        manager = new maskManager();
    }

    public void startLearning() {
        /*
         * 1. Начинаем проходить по всем уровням [1;10]. Получаем все маски для
         * данного уровня, см пункт 3.
         */
        for (int level = 1; level < Regression.countFields; level++) {
            ArrayList<Mask> masks;
            masks = manager.getLevel(level);
            /*
             * 2. Проходимся по всем полученным маскам и для каждой необходимо
             * найти свои коэффициенты. Для начала, объявляем матрицу размеров
             * level+1 на level +2. Почему +1 и +2 ? Отвечаю: количество строк
             * равно level + 1 потому, что если мы ищем коэф. для полинома, в
             * котором мы рассматриваем level неизвестных, то нам не следует
             * забывать, что мы так же ищем и значение а0. Количество столбцов
             * равно level +2 потому что помимо а0 у нас еще и y. Эта матрица
             * будет представлять собой систему уравнений, решив которую мы
             * получим значения коэф а0, а1,... для нашего полинома.
             */
            for (Mask mask : masks) {
                double[][] matrix;
                matrix = new double[level + 1][level + 2];
                           /*
                            * 3. Приступаем к формированию матрицы. Она формируется с
                помощью метода наименьших квадратов (опишу ниже).*/
                int number = 0;
                for (int iRow = 0; iRow < level + 1; iRow++) {
                    for (int iColumn = 0; iColumn < level + 2; iColumn++) {
                        matrix[iRow][iColumn] = 0;
                    }
                }
                for (int iRow = 0; iRow < level + 1; iRow++) {
                    for (int iMask = number; iMask < mask.getMask().size(); iMask++) {
                        if (mask.getMask().get(iMask) == 1) {
                            number = iMask;
                            break;
                        }
                    }

                    for (int iValues = 0; iValues < values.size(); iValues++) {
                        int iColumn = 0;

                        if (number > 0) {
                            matrix[iRow][iColumn] += 1 * values.get(iValues).getValue(number - 1);
                        } else {
                            matrix[iRow][iColumn] += 1;
                        }

                        iColumn++;
                        for (int iMask = 1; iMask < mask.getMask().size(); iMask++) {
                            if (mask.getMask().get(iMask) == 1) {
                                if (number > 0) {
                                    matrix[iRow][iColumn] += values.get(iValues).getValue(iMask - 1) * values.get(iValues).getValue(number - 1);
                                } else {
                                    matrix[iRow][iColumn] += values.get(iValues).getValue(iMask - 1) * 1;
                                }
                                iColumn++;
                            }
                        }
                        if (number > 0) {
                            matrix[iRow][iColumn] += values.get(iValues).getY1() * values.get(iValues).getValue(number - 1);
                        } else {
                            matrix[iRow][iColumn] += values.get(iValues).getY1() * 1;
                        }


                    }
                    number++;
                }
                polinoms.add(new Polinom(mask, findSolution(matrix, 0.0001)));
                System.out.println(polinoms.get(polinoms.size() - 1).printPolinom());
            }
            /*
             *
             * 4. После того, как мы сформировали матрицу, её необходимо решить
             * одним из методов решения СЛАУ. Для этого я использую метод
             * Зейделя. Решение СЛАУ не является предметом нашей ЛР, поэтому я
             * тебе скину готовый метод для решения СЛАУ, на вход метода
             * поступает матрица, а на выходе массив из найденных коэф. *
             */
        }
    }

    public void testValues() {
        double sko;
        double min = 1000;
        int imin = 0;

        for (int i = 0; i < polinoms.size(); i++) {

            for (Value value : testValues) {
                double y = 0;
                int indexCoef = 0;
                for (int j = 0; j < polinoms.get(i).getMask().getMask().size(); j++) {
                    if (polinoms.get(i).getMask().getMask().get(j) == 1) {
                        if (j == 0) {
                            y += polinoms.get(i).getCoef().get(indexCoef) * 1;
                        } else {
                            y += polinoms.get(i).getCoef().get(indexCoef) * value.getValue(j - 1);
                        }
                        indexCoef++;
                    }
                }

                sko = Math.pow((value.getY1() - y), 2);
//                System.out.print("Polinom ");
//                System.out.print(i);
//                System.out.print("  ");
//                System.out.print(sko);
//                System.out.print("\n");

                polinoms.get(i).iteratorSKO(sko);
            }
            polinoms.get(i).sqrSKO(testValues.size());
            if (min > polinoms.get(i).getSKO()) {
                min = polinoms.get(i).getSKO();
                imin = i;
            }

        }
        System.out.print("\nminimal SKO:  ");
        System.out.print(min);
        System.out.print("\nminimal POLINOM:  ");
        System.out.print(polinoms.get(imin).printPolinom());
    }


    private double[] findSolution(double[][] matrix, double eps) {
        int size = matrix.length;
        double[] previousVariableValues = new double[size];
        for (int i = 0; i < size; i++) {
            previousVariableValues[i] = 0.0;
        }
        // Будем выполнять итерационный процесс до тех пор,
        // пока не будет достигнута необходимая точность или не закончится цикл
        for (int count = 0; count < 100; count++) {
            // Введем вектор значений неизвестных на текущем шаге
            double[] currentVariableValues = new double[size];

            // Посчитаем значения неизвестных на текущей итерации
            // в соответствии с теоретическими формулами
            for (int i = 0; i < size; i++) {
                // Инициализируем i-ую неизвестную значением
                // свободного члена i-ой строки матрицы
                currentVariableValues[i] = matrix[i][size];

                // Вычитаем сумму по всем отличным от i-ой неизвестным
                for (int j = 0; j < size; j++) {
                    // При m < i можем использовать уже посчитанные
                    // на этой итерации значения неизвестных
                    if (j < i) {
                        currentVariableValues[i] -= matrix[i][j] * currentVariableValues[j];
                    }

                    // При m > i используем значения с прошлой итерации
                    if (j > i) {
                        currentVariableValues[i] -= matrix[i][j] * previousVariableValues[j];
                    }
                }

                // Делим на коэффициент при i-ой неизвестной
                currentVariableValues[i] /= matrix[i][i];
            }

            // Посчитаем текущую погрешность относительно предыдущей итерации
            double error = 0.0;

            for (int i = 0; i < size; i++) {
                error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
            }

            // Если необходимая точность достигнута, то завершаем процесс
            if (error < eps) {
                break;
            }

            // Переходим к следующей итерации, так
            // что текущие значения неизвестных
            // становятся значениями на предыдущей итерации
            previousVariableValues = currentVariableValues;
        }

        return previousVariableValues;
    }

    public ArrayList<Value> getValues() {
        return values;
    }

    public void setValues(ArrayList<Value> Values) {
        this.values = Values;
    }

    public maskManager getManager() {
        return manager;
    }

    public void setManager(maskManager manager) {
        this.manager = manager;
    }

    public ArrayList<Value> getTestValues() {
        return testValues;
    }

    public void setTestValues(ArrayList<Value> testValues) {
        this.testValues = testValues;
    }

}
