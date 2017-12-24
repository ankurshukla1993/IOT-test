package com.getkeepsafe.relinker.elf;

import com.getkeepsafe.relinker.elf.Elf.DynamicStructure;
import com.getkeepsafe.relinker.elf.Elf.Header;
import com.getkeepsafe.relinker.elf.Elf.ProgramHeader;
import com.getkeepsafe.relinker.elf.Elf.SectionHeader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Elf64Header extends Header {
    private final ElfParser parser;

    public Elf64Header(boolean bigEndian, ElfParser parser) throws IOException {
        this.bigEndian = bigEndian;
        this.parser = parser;
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = parser.readHalf(buffer, 16);
        this.phoff = parser.readLong(buffer, 32);
        this.shoff = parser.readLong(buffer, 40);
        this.phentsize = parser.readHalf(buffer, 54);
        this.phnum = parser.readHalf(buffer, 56);
        this.shentsize = parser.readHalf(buffer, 58);
        this.shnum = parser.readHalf(buffer, 60);
        this.shstrndx = parser.readHalf(buffer, 62);
    }

    public SectionHeader getSectionHeader(int index) throws IOException {
        return new Section64Header(this.parser, this, index);
    }

    public ProgramHeader getProgramHeader(long index) throws IOException {
        return new Program64Header(this.parser, this, index);
    }

    public DynamicStructure getDynamicStructure(long baseOffset, int index) throws IOException {
        return new Dynamic64Structure(this.parser, this, baseOffset, index);
    }
}
