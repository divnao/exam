import java.io.File;
import java.util.logging.Logger;

/**
 * 目录遍历
 *
 * @author huh
 */
public class Exam4 {

    public static void main(String[] args) {
        recursiveFile("/home/huh/Videos/03、中台201807培训资料(全)");
    }

    /**
     * print the absolute path of the giving file.
     *
     * @param isDir, a boolean value, is directory or not.
     * @param currentFilePath, the giving file path.
     */
    public static void printFilePath(Boolean isDir, String currentFilePath) {
        if (isDir) {
            System.out.println("目录: " + currentFilePath);
        } else {
            System.out.println("文件: " + currentFilePath);
        }
    }

    /**
     * recursive the giving dir.
     *
     * @param filePath, user offered file path.
     */
    public static void recursiveFile(String filePath) {
        File file = new File(filePath);
        if (! file.isDirectory()) {
            printFilePath(false, file.getAbsolutePath());
        } else {
            File[] files = file.listFiles();
            for (File f: files) {
                if (f.isFile()) {
                    printFilePath(false, f.getAbsolutePath());
                } else {
                    printFilePath(true, f.getAbsolutePath());
                    recursiveFile(f.getAbsolutePath());
                }
            }
        }
    }

    /**
     * get a logger.
     */
    public static Logger getLogger() {
        return Logger.getLogger("Exam4");
    }
}
