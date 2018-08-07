import java.util.Scanner;
import java.util.logging.Logger;

public class Exam1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Double tax = getTax(readSalary(in));
        getLogger().info("所需要缴纳的税费为: " + tax);
    }

    /**
     * read user input salary.
     *
     * @param in, input
     * @return salary
     */
    public static int readSalary(Scanner in) {
        int salary = 0;
        try {
            salary = in.nextInt();
            return salary;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return salary;
    }

    /**
     * get tax
     *
     * @param salary, user input salary
     * @param tax,
     */
    public static Double getTax(int salary) {
        final int baseSalary = salary - 3500;
        if(baseSalary - 80000 >= 0) {
            return 1500 * 0.03 + (4500-1500) * 0.10 + (9000-4500) * 0.20 + (35000 - 9000) * 0.25 + (55000 - 35000) * 0.30 + (80000 - 55000) * 0.35 + (baseSalary - 80000) * 0.45;
        } else if (baseSalary - 55000 >= 0) {
            return 1500 * 0.03 + (4500-1500) * 0.10 + (9000-4500) * 0.20 + (35000 - 9000) * 0.25 + (55000 - 35000) * 0.30 + (baseSalary - 55000) * 0.35;
        } else if (baseSalary - 35000 >= 0) {
            return 1500 * 0.03 + (4500-1500) * 0.10 + (9000-4500) * 0.20 + (35000 - 9000) * 0.25 + (baseSalary - 35000) * 0.30;
        } else if (baseSalary - 9000 >=0) {
            return 1500 * 0.03 + (4500-1500) * 0.10 + (9000-4500) * 0.20 + (baseSalary - 9000) * 0.25 ;
        } else if (baseSalary - 4500 >= 0) {
            return 1500 * 0.03 + (4500-1500) * 0.10 + (baseSalary-4500) * 0.20;
        } else if(baseSalary - 1500 > 0) {
            return 1500 * 0.03 + (baseSalary-1500) * 0.10 ;
        } else {
            if(baseSalary > 0) {
                return baseSalary * 0.03;
            } else {
                return 0.0;
            }
        }
    }

    /**
     * get a logger.
     */
    public static Logger getLogger() {
        return java.util.logging.Logger.getLogger("Exam1");
    }



}
