/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.bytecode;

import org.gjt.jclasslib.io.*;
import java.io.*;

/**
    Base class for all opcode instruction wrappers.
 
    @author <a href="mailto:jclasslib@ej-technologies.com">Ingo Kegel</a>
    @version $Revision: 1.3 $ $Date: 2002/02/27 16:47:43 $
*/
public abstract class AbstractInstruction implements Opcodes {

    /** The opcode of this instruction */
    protected int opcode;
    /** The offset of this instruction in its parent <tt>Code</tt> attribute */
    protected int offset;
    
    public AbstractInstruction(int opcode) {
        this.opcode = opcode; 
    }
    
    /**
        Get the size in bytes of this instruction.
        @return the size in bytes
     */
    public int getSize() {
        return 1;
    }

    /**
        Get the opcode of this instruction.
        @return the opcode
     */
    public int getOpcode() {
        return opcode;
    }

    /**
        Set the opcode of this instruction.
        @param opcode the opcode
     */
    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    /**
        Get the verbose description of the opcode of this instruction.
        @return the description
     */
    public String getOpcodeVerbose() {
        String verbose = OpcodesUtil.getVerbose(opcode);
        if (verbose == null) {
            return "invalid opcode";
        } else {
            return verbose;
        }
    }
    
    /**
        Get the offset of this instruction in its parent <tt>Code</tt> attribute.
        @return the offset
     */
    public int getOffset() {
        return offset;
    }
    
    /**
        Set the offset of this instruction in its parent <tt>Code</tt> attribute.
        @param offset the offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    /**
        Read this instruction from the given <tt>ByteCodeInput</tt>. <p>
     
        Excpects <tt>ByteCodeInput</tt> to be in JVM class file format and just
        before a instruction of this kind.
        @param in the <tt>ByteCodeInput</tt> from which to read
        @throws IOException if an exception occurs with the <tt>ByteCodeInput</tt>
     */
    public void read(ByteCodeInput in) throws IOException {
        // The opcode has already been read
        offset = in.getBytesRead() - 1;
    }

    /**
        Write this instruction to the given <tt>ByteCodeOutput</tt>.
        @param out the <tt>ByteCodeOutput</tt> to which to write
        @throws IOException if an exception occurs with the <tt>ByteCodeOutput</tt>
     */
    public void write(ByteCodeOutput out) throws IOException {
        out.writeByte(opcode);
    }
    
}
