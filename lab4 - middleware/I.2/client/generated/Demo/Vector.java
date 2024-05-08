//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `calculator.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Demo;

public class Vector implements java.lang.Cloneable,
                               java.io.Serializable
{
    public int x;

    public int y;

    public Vector()
    {
    }

    public Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Vector r = null;
        if(rhs instanceof Vector)
        {
            r = (Vector)rhs;
        }

        if(r != null)
        {
            if(this.x != r.x)
            {
                return false;
            }
            if(this.y != r.y)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Demo::Vector");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, x);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, y);
        return h_;
    }

    public Vector clone()
    {
        Vector c = null;
        try
        {
            c = (Vector)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeInt(this.x);
        ostr.writeInt(this.y);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.x = istr.readInt();
        this.y = istr.readInt();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Vector v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public Vector ice_read(com.zeroc.Ice.InputStream istr)
    {
        Vector v = new Vector();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Vector> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Vector v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            ostr.writeSize(8);
            ice_write(ostr, v);
        }
    }

    static public java.util.Optional<Vector> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            istr.skipSize();
            return java.util.Optional.of(Vector.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Vector _nullMarshalValue = new Vector();

    /** @hidden */
    public static final long serialVersionUID = -1956635952L;
}