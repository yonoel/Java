import java.util.Map;
import java.util.Properties;

public class TestSys {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.list(System.out);
    }
}
