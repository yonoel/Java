package netty;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client();
        client.init();
    }

    @After
    public void tearDown() throws Exception {

    }

    //    @Test
    public void init() {
    }

    @Test
    public void doRequest() {
        client.doRequest();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}