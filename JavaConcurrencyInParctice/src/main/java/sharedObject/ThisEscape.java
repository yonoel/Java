package sharedObject;

import org.apache.catalina.Session;
import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventListener;
import java.util.concurrent.ExecutorService;

public class ThisEscape {
    public ThisEscape(ExecutorService service) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(111);
            }
        });
    }
}
