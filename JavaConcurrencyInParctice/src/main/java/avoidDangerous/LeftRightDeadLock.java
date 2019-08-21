package avoidDangerous;

public class LeftRightDeadLock {
    private Object left = new Object();
    private Object right = new Object();

    public void leftRight(){
        synchronized (left){
            synchronized (right){
                //do something
            }
        }
    }

    public void rightLeft(){
        synchronized (right){
            synchronized (left){
                // do something
            }
        }
    }
}
