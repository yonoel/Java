package cancelAndShutDown;

public class WorkThread implements Runnable {
    @Override
    public void run() {
        Throwable t = null;
        try {
            while (!Thread.currentThread().isInterrupted()){
//                runTask()
            }
        }catch (Throwable throwable){
            t  = throwable;
        }finally {
//            threadExit(this,t);
        }
    }
}
