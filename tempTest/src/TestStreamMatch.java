import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestStreamMatch {
    public static void main(String[] args) {
        Predicate predicate = (o) -> (int) o > 2;
        System.out.println(Stream.of(5, 0).anyMatch(predicate));
    }
}
