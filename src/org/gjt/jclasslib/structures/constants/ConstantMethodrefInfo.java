/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.structures.constants;

import org.gjt.jclasslib.structures.*;

import java.io.*;

/**
    Describes a <tt>CONSTANT_Methodref_info</tt> constant pool data structure.

    @author <a href="mailto:jclasslib@ej-technologies.com">Ingo Kegel</a>
    @version $Revision: 1.2 $ $Date: 2002/02/27 16:47:43 $
*/
public class ConstantMethodrefInfo extends ConstantReference {

    public byte getTag() {
        return CONSTANT_METHODREF;
    }

    public String getTagVerbose() {
        return CONSTANT_METHODREF_VERBOSE;
    }

    public void read(DataInput in)
        throws InvalidByteCodeException, IOException {
        
        super.read(in);
        if (debug) debug("read ");
    }
    
    public void write(DataOutput out)
        throws InvalidByteCodeException, IOException {
        
        out.writeByte(CONSTANT_METHODREF);
        super.write(out);
        if (debug) debug("wrote ");
    }
    
    protected void debug(String message) {
        super.debug(message + getTagVerbose() + " with class_index " + classIndex +
              " and name_and_type_index " + nameAndTypeIndex);
    }

}
