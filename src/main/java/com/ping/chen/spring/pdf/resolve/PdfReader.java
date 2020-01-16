package com.ping.chen.spring.pdf.resolve;

import org.apache.pdfbox.PDFReader;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;

public class PdfReader {

    public static void main(String[] args) throws IOException {
        File file = new File("测试1.pdf");
        PDFReader pdfReader = new PDFReader();

        PDDocument document = PDDocument.load(file);

        //Instantiate PDFTextStripper class
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setParagraphStart("    ");
        stripper.setPageStart("    ");

        //Retrieving text from PDF document
        String text = stripper.getText(document);
        System.out.println(text);

        //Closing the document
        document.close();
    }
    public static String getTextFromPdf(String filePath) {
        String result = null;
        FileInputStream is = null;
        PDDocument document = null;
        try {
            is = new FileInputStream(filePath);
            PDFParser parser = new PDFParser(is);
            parser.parse();
            document = parser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setParagraphStart("               ");
            stripper.setPageStart("          ");

            result = stripper.getText(document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public  static String getTextFromPDF(String pdfFilePath) {
        String result = null;
        FileInputStream is = null;
        PDDocument document = null;
        try {
            if(pdfFilePath.toUpperCase().endsWith(".TXT")){
                return null;
            }
            is = new FileInputStream(pdfFilePath);
            PDFParser parser = new PDFParser(is);
            parser.parse();
            document = parser.getPDDocument();
            System.out.print(document);
            PDFTextStripper stripper = new PDFTextStripper();
            System.out.println(pdfFilePath);
            result = stripper.getText(document);
            FileWriter fw = new FileWriter(pdfFilePath+".txt",false);
            fw.write(result);
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pdfFilePath+".txt";
    }
}
