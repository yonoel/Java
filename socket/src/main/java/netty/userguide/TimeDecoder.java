package netty.userguide;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/24
 */
// decoder相当于一个过滤器
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String rev = in.toString(CharsetUtil.UTF_8);
        if (rev.length() < 4){
            return;
        }
        // decode 一定要使in被读取过,比如decode生成一个object但是in没读，那继续生成object，无限bug，为避免此类情况
        // 如果in没有被读取过，即reader依旧在那个位置
//        out.add(in.readBytes(4));
        out.add(new UnixTime(in.readUnsignedByte()));
//        out.add(in);
        /*if (rev.contains("test")) {
            ByteBuf copy = in.retainedDuplicate();
            out.add(copy);
        } else {
            in.release();
            return;
        }*/

//        System.out.println("decode:"+in.readableBytes());
//        if (in.readableBytes() <4) {
//            System.out.println(in.toString(CharsetUtil.UTF_8));
//            return; // (3)
//        }
//
//        out.add(in.readBytes(4)); // (4)
//        System.out.println("out size"+out.size());
    }
}
