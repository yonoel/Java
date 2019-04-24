package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.DEFAULT)
public class TestByteBufTest {
    ByteBuf byteBuf;

    @Before
    public void backingArray() {
        byteBuf = Unpooled.buffer(6);
    }

    @Test()
    public void write() {
        System.out.println("write");
        for (int i = 0; i < 6; i++) {
            byteBuf.writeByte(i + 1);
        }
        read();
        discard();
//        System.out.println("before" + byteBuf.writerIndex());
//        ByteBuf byteBuff = byteBuf.resetWriterIndex();
//        System.out.println("after" + byteBuff.writerIndex());
//        read();
    }


    @Test
    public void directBuf() {
    }

    @Test
    public void compositeBuf() {
    }

    public void read() {
        System.out.println("read");
        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.print(byteBuf.getByte(i));
        }
        System.out.println();
//        System.out.println("reader length" + byteBuf.readerIndex());
    }

    //    对于已经读过的字节，我们需要回收，通过调用ByteBuf.discardReadBytes()来回收已经读取过的字节，discardReadBytes()将回收从索引0到readerIndex之间的字节。调用discardReadBytes()方法之后会变成如下图所示;
    public void discard() {
        for (int i = 0; i < byteBuf.capacity() / 2; i++) {
            System.out.println(i);
            byteBuf.readerIndex(i);
        }
        System.out.println();
//        System.out.println("reader length" + byteBuf.readerIndex());
        ByteBuf DIS = byteBuf.discardReadBytes();
        for (int i = 0; i < DIS.capacity(); i++) {
            System.out.print(DIS.getByte(i));
        }
//        System.out.println("after dis reader length" + byteBuf.readerIndex());
    }
}