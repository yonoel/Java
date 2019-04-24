package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/23
 */
public class TestByteBuf {
    // JDK中的Buffer的类型 有heapBuffer和directBuffer两种类型，
    // 但是在netty中除了heap和direct类型外，还有composite Buffer(复合缓冲区类型)。
    public static void main(String[] args) {
        new TestByteBuf().backingArray();
    }

    /*2.2.1、Heap Buffer 堆缓冲区
    这是最常用的类型，ByteBuf将数据存储在JVM的堆空间，通过将数据存储在数组中实现的。
            1）堆缓冲的优点是：由于数据存储在JVM的堆中可以快速创建和快速释放，并且提供了数组的直接快速访问的方法。
            2）堆缓冲缺点是：每次读写数据都要先将数据拷贝到直接缓冲区再进行传递。
    这种模式被称为支撑数组 （backing array），它能在没有使用池化的情况下提供快速的分配和释放。这种方式，如代码清单 5-1 所示，非常适合于有遗留的数据需要处理的情况。
*/
    public void backingArray() {
        ByteBuf heapBuf = Unpooled.buffer();
        heapBuf.writeByte(1);
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            System.out.println(offset);
            int length = heapBuf.readableBytes();
            System.out.println(length);

        }
    }

    /*2.2.2、Direct Buffer 直接缓冲区
        NIO 在 JDK 1.4 中引入的 ByteBuffer 类允许 JVM 实现通过本地调用来分配内存。这主要是为了避免在每次调用本地 I/O 操作之前（或者之后）将缓冲区的内容复 制到一个中间缓冲区（或者从中间缓冲区把内容复制到缓冲区）。

        Direct Buffer在堆之外直接分配内存，直接缓冲区不会占用堆的容量。事实上，在通过套接字发送它之前，JVM将会在内部把你的缓冲 区复制到一个直接缓冲区中。所以如果使用直接缓冲区可以节约一次拷贝。

                （1）Direct Buffer的优点是：在使用Socket传递数据时性能很好，由于数据直接在内存中，不存在从JVM拷贝数据到直接缓冲区的过程，性能好。

                （2）缺点是：相对于基于堆的缓冲区，它们的分配和释放都较为昂贵。如果你 正在处理遗留代码，你也可能会遇到另外一个缺点：因为数据不是在堆上，所以你不得不进行一 次复制。*/
//    不过对于一些IO通信线程中读写缓冲时建议使用DirectByteBuffer，因为这涉及到大量的IO数据读写。对于后端的业务消息的编解码模块使用HeapByteBuffer。
    public void directBuf() {
        ByteBuf byteBuf = Unpooled.buffer();
        if (!byteBuf.hasArray()) {
            int length = byteBuf.readableBytes();
//            byteBuf.getBytes()
        }
    }

    /* 2.2.3、Composite Buffer 复合缓冲区
     第三种也是最后一种模式使用的是复合缓冲区，它为多个 ByteBuf 提供一个聚合视图。在 这里你可以根据需要添加或者删除 ByteBuf 实例，这是一个 JDK 的 ByteBuffer 实现完全缺 失的特性。

     Netty 通过一个 ByteBuf 子类——CompositeByteBuf——实现了这个模式，它提供了一 个将多个缓冲区表示为单个合并缓冲区的虚拟表示

     Netty提供了Composite ByteBuf来处理复合缓冲区。例如：一条消息由Header和Body组成，将header和body组装成一条消息发送出去。*/
    public void compositeBuf() {
        //组合缓冲区
        CompositeByteBuf compBuf = Unpooled.compositeBuffer();
        //堆缓冲区
        ByteBuf heapBuf = Unpooled.buffer(8);
        //直接缓冲区
        ByteBuf directBuf = Unpooled.directBuffer(16);
        //添加ByteBuf到CompositeByteBuf
        compBuf.addComponents(heapBuf, directBuf);
        //删除第一个ByteBuf
        compBuf.removeComponent(0);
        Iterator<ByteBuf> iter = compBuf.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }

        //使用数组访问数据
        if (!compBuf.hasArray()) {
            int len = compBuf.readableBytes();
            byte[] arr = new byte[len];
            compBuf.getBytes(0, arr);
        }
    }

    public void read() {
        //创建一个16字节的buffer,这里默认是创建heap buffer
        ByteBuf buf = Unpooled.buffer(16);
        //写数据到buffer
        for (int i = 0; i < 16; i++) {
            buf.writeByte(i + 1);
        }
        System.out.println(buf.readerIndex());
        System.out.println(buf.writerIndex());
        //读数据
        for (int i = 0; i < buf.capacity(); i++) {
            System.out.print(buf.getByte(i) + ", ");
        }
        System.out.println(buf.readerIndex());
        System.out.println(buf.writerIndex());
        // 这种方式readerIndex和writerIndex不会改变
    }
    public void discardbuf(){

    }
}
