/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.structures.attributes;

import org.gjt.jclasslib.structures.AttributeInfo;
import org.gjt.jclasslib.structures.InvalidByteCodeException;

import java.io.*;

/**
    Describes an <tt>LocalVariableTable</tt> attribute structure.

    @author <a href="mailto:jclasslib@ej-technologies.com">Ingo Kegel</a>
    @version $Revision: 1.3 $ $Date: 2003/07/08 14:04:29 $
*/
public class LocalVariableTableAttribute extends AttributeInfo {

    /** Name of the attribute as in the corresponding constant pool entry */
    public static final String ATTRIBUTE_NAME = "LocalVariableTable";

    private static final int INITIAL_LENGTH = 2;

    private LocalVariableTableEntry[] localVariableTable;
    
    /**
        Get the list of local variable associations of the parent
        <tt>Code</tt> structure as an array of <tt>LocalVariableTableEntry</tt> structures.
        @return the array
     */
    public LocalVariableTableEntry[] getLocalVariableTable() {
        return localVariableTable;
    }
    
    /**
        Set the list of local variable associations of the parent
        <tt>Code</tt> structure as an array of <tt>LocalVariableTableEntry</tt> structures.
        @param localVariableTable the index
     */
    public void setLocalVariableTable(LocalVariableTableEntry[] localVariableTable) {
        this.localVariableTable = localVariableTable;
    }
    
    public void read(DataInput in)
        throws InvalidByteCodeException, IOException {
            
        int localVariableTableLength = in.readUnsignedShort();
        localVariableTable = new LocalVariableTableEntry[localVariableTableLength];
        for (int i = 0 ; i < localVariableTableLength; i++) {
            localVariableTable[i] = LocalVariableTableEntry.create(in, classFile);
        }
        
        if (debug) debug("read ");
    }

    public void write(DataOutput out)
        throws InvalidByteCodeException, IOException {
        
        super.write(out);

        int localVariableTableLength = getLength(localVariableTable);
        
        out.writeShort(localVariableTableLength);
        for (int i = 0 ; i < localVariableTableLength; i++) {
            localVariableTable[i].write(out);
        }
        if (debug) debug("wrote ");
    }

    public int getAttributeLength() {
        return INITIAL_LENGTH + getLength(localVariableTable) * LocalVariableTableEntry.LENGTH;
    }

    protected void debug(String message) {
        super.debug(message + "LocalVariableTable attribute with " + getLength(localVariableTable) + " entries");
    }

}
