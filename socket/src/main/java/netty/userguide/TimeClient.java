package netty.userguide;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/24
 */
public class TimeClient {
    public static void main(String[] args) {
        try {
            TimeClient client = new TimeClient("127.0.0.1", 8080);
            client.run();
//            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int port;
    private String host;
    private EventLoopGroup work;
    private Bootstrap bootstrap;

    public TimeClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        config();
        // Start the client.
        ChannelFuture f = bootstrap.connect(host, port).sync(); // (5)

        // Wait until the connection is closed.
        f.channel().closeFuture().sync();
    }

    public void config() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
// If you specify only one EventLoopGroup, it will be used both as a boss group and as a worker group.
// The boss worker is not used for the client side though.
        this.work = workerGroup;
        Bootstrap b = new Bootstrap(); // (1)
        this.bootstrap = b;
        b.group(workerGroup); // (2)
        b.channel(NioSocketChannel.class); // (3)
        b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeDecoder(),new TimeClientHandler());
            }
        });
    }
    public void close(){
        work.shutdownGracefully();
    }
}
