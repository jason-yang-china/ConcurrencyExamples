package com.epam;

import org.junit.Test;

import java.nio.ByteBuffer;

import static junit.framework.TestCase.assertTrue;

public class NIOFlipTest {

    @Test
    public void testFlip() {
        ByteBuffer b = ByteBuffer.allocate(16);

        printPositionAndLimit(b);
        b.put((byte)60);
        b.put((byte)65);
        b.put((byte)12);
        printPositionAndLimit(b);

        b.flip();
        printPositionAndLimit(b);

        System.out.println(b.get()+" "+b.get()+" "+b.get());

        printPositionAndLimit(b);
        //b.get();
        b.rewind();
        b.put((byte)70);
        printPositionAndLimit(b);
        b.get();
        //b.rewind();
        printPositionAndLimit(b);
        b.clear();
        printPositionAndLimit(b);
    }

    private void printPositionAndLimit(ByteBuffer byteBuffer) {
        System.out.println("position="+byteBuffer.position()+"limit="+byteBuffer.limit());
    }
}
