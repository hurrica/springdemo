package com.bluepay.spring.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {

        FileInputStream fis1 = new FileInputStream(new File("C:\\Users\\14119\\Desktop\\award\\TOT\\report_TOT Daily_electronic29092017(解释版).xlsx"));
        //
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\14119\\Desktop\\award\\TOT\\test.pdf"));
        //
        List<ExcelObject> objects = new ArrayList<ExcelObject>();
        objects.add(new ExcelObject("test.xls",fis1));

//
        Excel2Pdf pdf = new Excel2Pdf(objects , fos);
        pdf.convert();
    }
}
