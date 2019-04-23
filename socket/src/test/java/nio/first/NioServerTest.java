package nio.first;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class NioServerTest {
    @Test
    public void init() {
        try {
            new NioServer().init(8080).listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}