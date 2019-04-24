package netty.userguide;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/24
 */
public class TimeServer {
    public static void main(String[] args) {
        try {
            new DiscardServer(8080).run(new TimeEncoder(),new TimeServerHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
