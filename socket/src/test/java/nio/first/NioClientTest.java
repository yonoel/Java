package nio.first;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class NioClientTest {

    @Test
    public void init() {
        try {
            new NioClient().init("127.0.0.1",8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}