package rn2012a.dataPack;

import java.nio.charset.Charset;

public class DataAddr extends DataBox
{

    private String address;

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public int getLen()
    {
        // TODO Auto-generated method stub
        Charset charset = Charset.forName("UTF-8");
        return address.getBytes(charset).length;
    }

    @Override
    public String toString()
    {
        return "DataAddr [address=" + address + "]";
    }

   
}
