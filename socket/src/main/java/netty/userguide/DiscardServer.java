package netty.userguide;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/24
 */
public class DiscardServer {
    private int port;
    private ServerBootstrap bootstrap;
    private List<EventLoopGroup> eventLoopGroups = new LinkedList<>();

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run(ChannelHandler... channelHandlers) throws Exception {
        configServer(channelHandlers);
        // Bind and start to accept incoming connections.
        ChannelFuture f = bootstrap.bind(port).sync(); // (7)

        // Wait until the server socket is closed.
        // In this example, this does not happen, but you can do that to gracefully
        // shut down your server.
        f.channel().closeFuture().sync();

//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();

    }

    public void configServer(ChannelHandler... channelHandlers) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        eventLoopGroups.add(bossGroup);
        eventLoopGroups.add(workerGroup);
//        The first one, often called 'boss', accepts an incoming connection.
//        The second one, often called 'worker', handles the traffic of tasdhe accepted connection once the boss accepts the connection and registers the accepted connection to the worker.
//        How many Threads are used and how they are mapped to the created Channels depends on the EventLoopGroup implementation and may be even configurable via a constructor.
        ServerBootstrap b = new ServerBootstrap(); // (2)
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
// Here, we specify to use the NioServerSocketChannel class which is used to instantiate a new Channel to accept incoming connections.
                .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                    //The handler specified here will always be evaluated by a newly accepted Channel. The ChannelInitializer is a special handler that is purposed to help a user configure a new Channel.
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        for (int i = 0; i < channelHandlers.length; i++) {
                            ch.pipeline().addLast(channelHandlers[i]);

                        }
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
        this.bootstrap = b;
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new DiscardServer(port).run(new DiscardServerHandler());
    }
}
