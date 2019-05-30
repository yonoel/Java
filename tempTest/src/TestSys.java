import java.io.File;
import java.util.Map;
import java.util.Properties;

public class TestSys {
    public static void main(String[] args) {
//        Properties properties = System.getProperties();
//        properties.list(System.out);
        String newFileDIRs = "f://download/";
        System.out.println(newFileDIRs.substring(newFileDIRs.indexOf(File.separator)));
    }
}
