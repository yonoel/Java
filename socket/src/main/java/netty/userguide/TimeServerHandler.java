package netty.userguide;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/24
 */
@ChannelHandler.Sharable
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    // how to construct and send a message, and to close the connection on completion.
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
//As explained, the channelActive() method will be invoked when a connection is established and ready to generate traffic
//        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        String msg = "test";
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//        System.out.println(time.readableBytes());
//        time.writeBytes(msg.getBytes(CharsetUtil.UTF_8));
//        time.writeInt((int) (1)).writeByte(2).writeByte(10).writeByte(1).writeBytes(msg.getBytes());
//        System.out.println(time.readableBytes());

//        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        final  ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        // A  ChannelFuture represents an I/O operation which has not yet occurred.
        /**
         * It means, any requested operation might not have been performed yet because all operations are asynchronous in Netty.
         * For example, the following code might close the connection even before a message is sent:
         * Channel ch = ...;
         * ch.writeAndFlush(message);
         * ch.close();
         */
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
//                assert f == future;
//                ctx.close();
            }
        }); // (4)
//How do we get notified when a write request is finished then?
// This is as simple as adding a ChannelFutureListener to the returned ChannelFuture.
// Here, we created a new anonymous ChannelFutureListener which closes the Channel when the operation is done.


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
