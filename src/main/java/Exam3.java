import java.util.*;
import java.util.logging.Logger;

/**
 * Map中的List排序
 *
 */
public class Exam3 {

    public static void main(String[] args) {
        List<Integer> list = getRandomList();
        printFiftyNum(list);
        fiftyRandom(list);
    }

    public static double getRandomNum() {
        return Math.random() * 100;
    }

    public static List<Integer> getRandomList() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            list.add((int) getRandomNum());
        }
        return list;
    }

    public static Map<Integer, ArrayList<Integer>> fiftyRandom(List<Integer> list) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for(Integer i : list) {
            ArrayList<Integer> tmpList = new ArrayList<Integer>();
            int k = i / 10;
            for(Integer j: list) {
                if (String.valueOf(j).startsWith(String.valueOf(k))) {
                    tmpList.add(j);
                }
            }
            Collections.sort(tmpList, new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            map.put(k, tmpList);
        }
        System.out.print("排序后的Map：");
        System.out.println(map);
        return map;
    }

    public static void printFiftyNum(List<Integer> list) {
        System.out.print("50个整数: ");
        for(Integer num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    /**
     * get a logger.
     */
    public static Logger getLogger() {
        return java.util.logging.Logger.getLogger("Exam1");
    }


}
