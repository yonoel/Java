package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ExecutionException;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/23
 */
public class Client {
    private Bootstrap bootstrap;
    public Channel channel;

    public Client() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap(); // (1)
        b.group(workerGroup); // (2)
        b.channel(NioSocketChannel.class); // (3)
        b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
        b.handler(new ChannelInitializer<SocketChannel>() {

                      @Override
                      protected void initChannel(SocketChannel ch) throws Exception {
                          ch.pipeline().addFirst(
                                  new InHandler1()
                          );
                      }
                  }

        );
        this.bootstrap = b;
    }

    public void init() throws Exception {
        // Start the client.
        Channel channel = bootstrap.connect("192.168.1.143", 8080)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE)
                .sync().channel(); // (5)

        this.channel = channel;
        System.out.println("init");

    }

    public void doRequest() {

        ByteBuf byteBuf = Unpooled.buffer(16);
        for (int i = 0; i < byteBuf.capacity(); i++) {
            byteBuf.writeByte(i+1);
//            byteBuf.writeChar('a')阻塞了？？？
        }
//        channel.write(byteBuf);
        channel.writeAndFlush(byteBuf)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }
}
