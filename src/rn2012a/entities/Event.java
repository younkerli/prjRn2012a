package rn2012a.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import rn2012a.dataPack.DataEvent;

public class Event extends FileOperate
{
//    private List<DataEvent> evtdata;
//
//    public void setEvtdata( List <DataEvent> evtdata)
//    {
//        this.evtdata = evtdata;
//    }

    private static String separator = "--";

     private String filepath =
     this.getClass().getClassLoader().getResource("").getPath().replace("%20",
     " ") + "files/event/";
//    private String filepath = "C:/prjData/files/event/";
    private String filename = null;

    public void save(Integer devId, List<DataEvent> evtdata)
    {
        filename = "dev" + devId + ".txt";
        FileWriter fileWriter = null;
        try
        {
            File file = new File(filepath + filename);
            if (!file.exists())
            {
                System.out.println(filename + "文件不存在！");
            }
            fileWriter = new FileWriter(file);
            for (DataEvent dataEvent : evtdata)
            {
                String string = dataEvent.getTm().strDateInfo() + separator + dataEvent.getEvent() + separator + dataEvent.getVoltage() + separator + dataEvent.getPharse() + "\r\n";
                fileWriter.write(string);
            }
//            writer.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try
            {
                fileWriter.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void loadEvts(Integer devId, List<DataEvent> evtdata)
    {
        filename = "dev" + devId + ".txt";
        File file = new File(filepath + filename);
        if (!file.exists())
        {
            System.out.println("文件不存在！");
            return;
        }
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            try
            {
                String string = null;
                String[] tmpStrs = null;
                while ((string = reader.readLine()) != null)
                {
                    tmpStrs = string.split(separator);
                    evtdata.add(new DataEvent(tmpStrs));
                }
            } catch (Exception e)
            {
                // TODO: handle exception
                e.printStackTrace();
            }
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            System.out.println( filename + "读取失败！");
            e.printStackTrace();
        } finally {
            try
            {
                reader.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public boolean InitFileByDevId(Integer devId)
    {
        return initFile(filepath, "dev" + devId + ".txt");
    }
}
