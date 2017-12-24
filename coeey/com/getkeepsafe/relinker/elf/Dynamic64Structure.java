package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf.DynamicStructure;
import com.getkeepsafe.relinker.elf.Elf.Header;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Dynamic64Structure extends DynamicStructure {
    public Dynamic64Structure(ElfParser parser, Header header, long baseOffset, int index) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        baseOffset += (long) (index * 16);
        this.tag = parser.readLong(buffer, baseOffset);
        this.val = parser.readLong(buffer, 8 + baseOffset);
    }
}
