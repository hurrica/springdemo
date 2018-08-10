package com.bluepay.spring.util;

import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyPdfUtil {

    private String fontFile = this.getClass().getResource("THSarabunNew.ttf").getPath();

    public static void main(String[] args) throws Exception {
        System.out.println(new MyPdfUtil().fontFile);

        Document document = new Document(PageSize.A4, 20, 20, 50, 20);

        File outputFile = new File("a.pdf");
        //设置输出位置
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        //打开文档
        document.open();
        PdfPTable table = new PdfPTable(13);
        BaseFont baseFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont,
                AsianFontMapper.ChineseSimplifiedEncoding_H, BaseFont.NOT_EMBEDDED);
        Font font =  FontFactory.getFont(new MyPdfUtil().fontFile, BaseFont.IDENTITY_H, false);

        table.setSpacingBefore(20f);
        //构造表头
        buildHeader(table, font);
        document.add(table);

        document.close();

    }

    public void test() throws IOException, DocumentException {
        BaseFont baseFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont,
                AsianFontMapper.ChineseSimplifiedEncoding_H, BaseFont.NOT_EMBEDDED);
    }
    private static void buildHeader(PdfPTable table, Font baseFont) {

        table.addCell(new PdfPCell());
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setColspan(10);
        pdfPCell.setPhrase(new Phrase("test"));
        table.addCell(pdfPCell);
        table.addCell(new PdfPCell());
        table.addCell(new PdfPCell());


        table.addCell(new PdfPCell());
        pdfPCell = new PdfPCell();
        pdfPCell.setColspan(10);
        pdfPCell.setPhrase(new Phrase("测试", baseFont));
        table.addCell(pdfPCell);
        table.addCell(new PdfPCell());
        table.addCell(new PdfPCell());


        table.addCell(new PdfPCell());
        pdfPCell = new PdfPCell();
        pdfPCell.setColspan(10);
        pdfPCell.setPhrase(new Paragraph("บริษัท บลูเพย์ จำกัด", baseFont));
        table.addCell(pdfPCell);
        table.addCell(new PdfPCell());
        table.addCell(new PdfPCell());
    }
}
