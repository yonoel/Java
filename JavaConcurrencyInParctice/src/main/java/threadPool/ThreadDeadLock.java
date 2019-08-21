package threadPool;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDeadLock {
    ExecutorService service = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String>{
        @Override
        public String call() throws Exception {
            Future<String> header,footer;
            header = service.submit(()->{
                File file = new File("header.html");
                return file.getName();
            });
            footer = service.submit(()->{
                File file = new File("footer.html");
                return file.getName();
            });
            String page = renderPageBody();
            // 死锁，由于此任务在等待子任务的完成
            return header.get() + page + footer.get();
        }

        private String renderPageBody() {
            return null;
        }
    }
}
