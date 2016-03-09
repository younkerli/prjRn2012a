package rn2012a.dataPack;

public class DataEvent extends DataBox
{

    private TimeInfo tm;
    private int event;
    private float voltage;
    private String pharse;
    private int reserved;
    private int devId;

    public DataEvent(String[] tmpStrs)
    {
        Integer time = Integer.valueOf(tmpStrs[1]);
        tm = new TimeInfo();
        tm.setMinute(time % 100);
        time /= 100;
        int hour = time % 100;
        if (hour < 12)
        {
            tm.setHour(hour);
            tm.setAmpm(0);
        } else {
            tm.setHour(hour - 12);
            tm.setAmpm(1);
        }
        time /= 100;
        tm.setDate(time % 100);
        time /= 100;
        tm.setMonth(time% 100);
        time /= 100;
        tm.setYear(time % 100);
        tm.setSecond(0);
        tm.setWeek(0);
        
        event = Integer.valueOf(tmpStrs[2]);
        voltage = Float.valueOf(tmpStrs[3]);
        pharse = tmpStrs[4];
        
        reserved = 0;
        devId = 0;
    }

    public DataEvent()
    {
    }
    
    public int getDevId()
    {
        return devId;
    }

    public void setDevId(int devId)
    {
        this.devId = devId;
    }

    public TimeInfo getTm()
    {
        return tm;
    }

    public void setTm(TimeInfo tm)
    {
        this.tm = tm;
    }

    public int getEvent()
    {
        return event;
    }

    public void setEvent(int event)
    {
        this.event = event;
    }

    public float getVoltage()
    {
        return voltage;
    }

    public void setVoltage(int voltage)
    {
        this.voltage = voltage;
        if (this.event == 14)
        {
            this.voltage /= 1000;
        } else
        {
            this.voltage /= 100;
        }
    }

    public String getPharse()
    {
        return pharse;
    }

    public void setPharse(String pharse)
    {
        this.pharse = pharse;
    }

    public int getReserved()
    {
        return reserved;
    }

    public void setReserved(int reserved)
    {
        this.reserved = reserved;
    }

    @Override
    public int getLen()
    {
        return tm.getLen() + 2 + 2 + 1 + 1;
    }

    @Override
    public String toString()
    {
        return "DataEvent [tm=" + tm + ", event=" + event + ", voltage=" + voltage + ", pharse=" + pharse
                + ", reserved=" + reserved + ", devId=" + devId + "]";
    }

}
