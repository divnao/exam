import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.util.*;
import java.util.logging.Logger;

/**
 * 随机点名
 *
 * @author huh
 */
public class Exam5 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/home/huh/Desktop/student_list.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            Map<Integer, Map<Integer, String>> map = getStudentList(workbook);
            System.out.println("总人数：" + map.size());
            System.out.print("输入点名人数: ");
            int num = userInput(in);
            callStudent(map, num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    public static void callStudent(Map<Integer, Map<Integer, String>> map, int num) {
        if (num > map.size()) {
            System.out.println("超过总人数, 全部点名：");
            num = map.size();
        }
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < num) {
            set.add(getRandomNum(map.size()));
        }
        System.out.println("工号" + "\t" + "姓名");
        for (Integer i : set) {
            Iterator it = map.get(i).entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        }
    }

    /**
     * get a random which in the range of [0, totalStudent]
     *
     * @param totalStudent, student num.
     */
    public static int getRandomNum(int totalStudent) {
        double random = Math.random() * totalStudent;
        return (int) random;
    }

    /**
     * get user input num.
     *
     * @param in, input stream.
     * @return user input number.
     */
    public static int userInput(Scanner in) {
        try {
            return in.nextInt();
        } catch (Exception e) {
            System.out.println("输入不合法，请输入正整数！");
            System.exit(1);
        } finally {
            in.close();
        }
        return 0;
    }

    /**
     * get student list from user offered excel.
     *
     * @param workbook, an object of user offered excel.
     * @return map, student set.
     */
    public static Map<Integer, Map<Integer, String>> getStudentList(HSSFWorkbook workbook) {
        Map<Integer, Map<Integer, String>> student_map = new HashMap<Integer, Map<Integer, String>>();
        Iterator it = workbook.sheetIterator();
        // read sheet one by one.
        int i = 0;
        while (it.hasNext()) {
            HSSFSheet sheet = (HSSFSheet) it.next();
            Iterator<Row> rows = sheet.iterator();
            // read row one by one.
            while (rows.hasNext()) {
                Row row = rows.next();
                String name = row.getCell(2).toString();
                String id = row.getCell(4).toString();
                Map<Integer, String> map = new HashMap<Integer, String>();
                if (id.contains("工")) {
                    continue;
                } else {
                    int intID = Integer.parseInt(id.substring(0, id.indexOf(".")));
                    map.put(intID, name);
                    student_map.put(i, map);
                    i++;
                }
            }
        }
        return student_map;
    }

    /**
     * get a logger.
     */
    public static Logger getLogger() {
        return Logger.getLogger("Exam4");
    }

}