package rn2012a.frm;

import java.nio.charset.Charset;

import rn2012a.dataPack.DataAddr;

public class AddrFrm extends GeneralFrame
{

    private DataAddr dataAddr;

    public DataAddr getDataAddr()
    {
        if (dataAddr == null)
        {
            dataAddr = new DataAddr();
        }
        return dataAddr;
    }

    public void setDataAddr(DataAddr dataAddr)
    {
        this.dataAddr = dataAddr;
    }

    @Override
    public short getLength(Charset charset)
    {
        // TODO Auto-generated method stub
        int len = 0;
        if (this.dataAddr != null)
        {
            len = this.dataAddr.getLen();
        }
        return (short) (MINLEN + len);
    }

    @Override
    public short getCommandId()
    {
        // TODO Auto-generated method stub
        return 13;
    }

    @Override
    public String toString()
    {
        return "AddrFrm [dataAddr=" + dataAddr + "]";
    }

}
