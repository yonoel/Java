package com.study.book.Example.outOfMemory;

/**
 * VM ARGS:-Xss2m 推荐可以大一点
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {

        }
    }

    private void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(() -> {
                dontStop();
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM stackOOM = new JavaVMStackOOM();
        stackOOM.stackLeakByThread();
        //  注意此段代码可能导致系统死机。。不要轻易尝试
    }
}
