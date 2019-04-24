package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.nio.charset.Charset;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/23
 */
public class ObjectEchoClient {
    protected String id;
    protected ChannelFuture channelFuture;
    protected EventLoopGroup eventExecutors;
    protected Bootstrap bootstrap;
    protected ChannelPipeline channelPipeline;
    protected Channel channel;
    protected boolean loseConnection = true;
    static final boolean SSL = System.getProperty("ssl") != null;
    //        static final String HOST = System.getProperty("host", "47.99.41.212");
//        static final int PORT = Integer.parseInt(System.getProperty("port", "82"));
    static final String HOST = System.getProperty("host", "192.168.1.143");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));



    protected void handshake() throws Exception {
        ObjectEchoClient self = this;
        // Configure SSL.
        if (!this.loseConnection) return;

        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        this.eventExecutors = group;
        Bootstrap b = new Bootstrap();
        this.bootstrap = b;

        b.option(ChannelOption.SO_KEEPALIVE, true)
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        self.channelPipeline = channelPipeline;
                        ChannelPipeline p = ch.pipeline();
                        if (sslCtx != null) {
                            p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                        }
                        p.addLast(

//                                new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())),
//                                new ObjectEchoClientHandler(),
//                                new NettyClientOutBoundHandler()
                        );

                    }
                });

    }

    public void startClient() throws InterruptedException {
        // Start the connection attempt.
        Channel channel = this.bootstrap.connect(HOST, PORT)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE).sync().channel();
        this.channel = channel;
    }


    public void close() {
        if (eventExecutors != null) {
            eventExecutors.shutdownGracefully();
            loseConnection = true;
        }
    }



    public void doRequest(String st) {
        while (true) {
            if (channel.isWritable()) {
                ByteBuf firstMessage = Unpooled.buffer();
                firstMessage.writeBytes(st.getBytes(Charset.forName("UTF-8")));
                System.out.println("is writing-----------");
                channel.writeAndFlush(firstMessage)
                        .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
                break;
            }
        }
    }

    public void getResponse() {
        while (true) {
        }
    }


}
