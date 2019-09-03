package threadManagement;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf(" an exception has been captured %n");
        System.out.printf(" thread : %s %n",t.getId());
        System.out.printf(" exception is %s : %s %n",e.getClass().getName(),e.getMessage());
        System.out.printf(" stack trace : %n");
        e.printStackTrace(System.out);
        System.out.printf(" thread status is %s %n",t.getState());
    }
}
