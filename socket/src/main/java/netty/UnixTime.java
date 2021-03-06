package netty;

import java.util.Date;

/**
 * @program: Java
 * @description: pojo类的传输
 * @author: Qian Yi Zhen
 * @create: 2019/04/23
 */
public class UnixTime {
    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
