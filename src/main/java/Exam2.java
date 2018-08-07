import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Exam2 {

    /**
     * main function, read user input date, then print the next day in date formt.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInputDate = readDate(in);
        String nextDate = nextDay(userInputDate);
        printNextDay(nextDate);
    }

    /**
     * read user input date.
     *
     * @param in, input.
     * @return user input date.
     */
    public static String readDate(Scanner in) {
        try {
            getLogger().log(Level.ALL, "请输入日期：");
            return in.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return null;
    }

    /**
     * check the format of user input.
     *
     * @param date, user input date.
     * @return true, format avaliable, else false.
     */
    public static boolean checkInputFormat(String date) {
        if (! date.contains("-")) {
            return false;
        } else {
            String[] dates = date.split("-");
            if (dates.length != 3) {
                return false;
            } else {
                for(int i = 0 ; i < dates.length ; i ++){
                    try {
                        Integer.parseInt(dates[i]);
                    } catch (Exception e){
                        return false;
                    }
                }
                return true;
            }

        }
    }

    /**
     * compute the next day.
     *
     * @param date, user input date.
     * @return next day
     */
    public static String nextDay(String date) {
        printInfo(checkInputFormat(date));
        String[] dates = date.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);
        if (year > 0 && month > 0 &&  month <= 12 && day > 0 && day <= 31){
            if((year%4 == 0 && year % 100 != 0) || year % 400 == 0) { // 闰年
                if(month == 2 && day > 29) {
                    return null;
                } else if (month==1||month==3||month==2||month==5||month==7||month==8||month==10||month==12) {
                    if(month == 12 && day == 31) {
                        year += 1;
                        month = 1;
                        day = 1;
                    } else if (month == 2) {
                        if (day < 29) {
                            day +=1;
                        } else if (day == 29) {
                            month += 1;
                            day=1;
                        } else {
                            return null;
                        }
                    } else if (day == 31) {
                        month += 1;
                        day =1;
                    } else {
                        day +=1;
                    }
                    return year + "-" + month + "-" + day;
                } else {
                    if (day == 30) {
                        month +=1;
                        day=1;
                    } else {
                        day += 1;
                    }
                    return year + "-" + month + "-" + day;
                }

            } else {  // 平年
                if (month == 2 && day > 28) {
                    printInfo(false);
                } else if (month==1|| month == 2|| month==3||month==5||month==7||month==8||month==10||month==12) {
                    if(month == 12 && day == 31) {
                        year += 1;
                        month = 1;
                        day = 1;
                    } else if (month == 2) {
                        if (day < 28) {
                            day += 1;
                        } else if(day == 28) {
                            month += 1;
                            day = 1;
                        }else {
                           return null;
                        }
                    } else if (day == 31) {
                        month += 1;
                        day =1;
                    } else {
                        day +=1;
                    }
                } else {
                    if (day == 30){
                        month +=1;
                        day=1;
                    } else {
                        day += 1;
                    }
                }
            }
            return year + "-" + month + "-" + day;
        } else {
            printInfo(false);
        }
        return null;
    }

    public static void printNextDay(String nextDay) {
        if (nextDay==null){
            getLogger().warning("您输入的日期不存在");
            System.exit(1);
        } else {
            getLogger().info("下一天为:" + nextDay);
        }
    }

    /**
     * get a logger.
     */
    public static Logger getLogger() {
        return java.util.logging.Logger.getLogger("Exam2");
    }

    /**
     * print info in console.
     *
     * @param format, format is available or not.
     */
    public static void printInfo(boolean format) {
        if (!format) {
            getLogger().warning("您输入的日期不存在");
        }
    }

}