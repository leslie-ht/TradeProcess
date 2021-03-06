package com.ht.tradeprocess.service;


import com.ht.tradeprocess.entity.Report;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportGeneratorServiceTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void exportCsvWhenFileWhenContentIsEmpty() throws IOException {
        String path = "/Users/Leslie/Documents/workspace/tradeprocess/src/test/resource/outbound/expect/out.csv";
        File output = new File(path);
        List<Report> reportList = new ArrayList<>();
        ReportGeneratorService.exportCsv(output,reportList);
        String actual = readFromFile(path);
        String expect = null;
        assertEquals(expect,actual);
    }

    @Test
    public void exportCsvWhenFileWhenContentHasOneRecord() throws IOException {
        String path = "/Users/Leslie/Documents/workspace/tradeprocess/src/test/resource/outbound/expect/out.csv";
        File output = new File(path);
        Report report = new Report("ACC-1234","XYZ",150,"1234");
        List<Report> reportList = new ArrayList<>();
        reportList.add(report);
        ReportGeneratorService.exportCsv(output,reportList);

        String actual = readFromFile(path);
        String expect = "ACC-1234,XYZ,150,\"1234\"";
        assertEquals(expect,actual);
    }


    private String readFromFile(String path){
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileInputStream = new FileInputStream(new File(path));
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            //header
            bufferedReader.readLine();
            return bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(bufferedReader!=null) bufferedReader.close();
                if(inputStreamReader!=null) inputStreamReader.close();
                if(fileInputStream!=null) fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}