package netty;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectEchoClientTest {
    ObjectEchoClient client;

    @Before
    public void handshake() {
        client = new ObjectEchoClient();
        try {
            client.handshake();
            client.startClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void close() {
        client.close();
    }

    @Test
    public void doRequest() {
        client.doRequest("test");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getResponse() {
    }
}