public class TestCase {
    public static void main(String[] args) {
        TestCase testCase = new TestCase();
        Inner inner = testCase.new Inner<Boolean>();
        inner.data = true;
        System.out.println(inner.data.equals(true));
    /*
        String arg = "";
        int  i = Integer.valueOf(arg);
*/
    }
    class Inner<T>{
        public T data;
    }
}
