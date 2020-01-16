package com.ping.chen.spring.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class Excel2PdfUtil {

    public static Workbook create(InputStream inp) throws IOException, InvalidFormatException {
        if(!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if(POIFSFileSystem.hasPOIFSHeader(inp)) {
            return new HSSFWorkbook(inp);
        }
        if(POIXMLDocument.hasOOXMLHeader(inp)) {
            return new XSSFWorkbook(OPCPackage.open(inp));
        }
        throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
    }

    public static void excelToPdf(String path) throws Exception {
        FileInputStream input_document = new FileInputStream(new File("C:\\Users\\14119\\Desktop\\award\\TOT\\report_TOT Daily_electronic29092017(解释版).xlsx"));
        // Read workbook into HSSFWorkbook
        XSSFWorkbook my_xls_workbook = new XSSFWorkbook(input_document);
        // Read worksheet into HSSFSheet
        XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
        // To iterate over the rows
        Iterator<Row> rowIterator = my_worksheet.iterator();
        //We will create output PDF document objects at this point
        Document iText_xls_2_pdf = new Document();
        PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream("C:\\Users\\14119\\Desktop\\award\\TOT\\test.pdf"));
        iText_xls_2_pdf.open();



        PdfPTable table = new PdfPTable(13);

        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next(); //Fetch CELL
                PdfPCell table_cell=new PdfPCell(new Phrase(getValueFromCell(cell)));
                //feel free to move the code below to suit to your needs
                table.addCell(table_cell);
                //next line
            }

        }
        //Finally add the table to PDF document
        iText_xls_2_pdf.add(table);
        iText_xls_2_pdf.close();
        //we created our pdf file..
        input_document.close(); //close xls
    }

    private static String getValueFromCell(Cell cell) {

        switch (cell.getCellType()){
            case 0:return String.valueOf(cell.getNumericCellValue());
            case 1:return cell.getStringCellValue();
            case 2:return cell.getCellFormula();
            case 3:return "";
            case 4:return String.valueOf(cell.getBooleanCellValue());
            default: return "error";
        }

        /**、
         * int CELL_TYPE_NUMERIC = 0;
         *     int CELL_TYPE_STRING = 1;
         *     int CELL_TYPE_FORMULA = 2;
         *     int CELL_TYPE_BLANK = 3;
         *     int CELL_TYPE_BOOLEAN = 4;
         *     int CELL_TYPE_ERROR = 5;
         */
    }

    public static void main(String[] args) throws Exception {
        excelToPdf("");
    }

}
