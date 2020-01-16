package com.ping.chen.spring.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TOT上报PDF文件生成工具
 */
@Slf4j
@Component
public class TOTReportPdfService {

    private String thaiFont = "./font/THSarabunNew.ttf";

    //字体格式
    Font font =  FontFactory.getFont(thaiFont, BaseFont.IDENTITY_H, false,-1.0F, -1);

    public void generatorPdf(String outPutFilePath, String headerSuffix){
        Document document = new Document(PageSize.A4.rotate(), 20, 20, 50, 20);
        File outputFile = createReportFile(outPutFilePath);
        try {
            //设置输出位置
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            //打开文档
            document.open();


            float[] columnWidths = {80, 200, 100,100,80,80,80,80,80,80,80,80};//表格每一列的宽度
            PdfPTable table = getTable(12, columnWidths);

            //表头
            generatorHeader(table,  headerSuffix);

            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
            log.error("TOTReportPdfService.generatorPdf 文件创建失败。{}", e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("TOTReportPdfService.generatorPdf 找不到文件{}", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private File createReportFile(String outPutFilePath) {
        File file = new File(outPutFilePath);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private PdfPTable getTable(int colNum, float[] columnWidths) {
        PdfPTable table = new PdfPTable(colNum);
        try {
            table.setWidths(columnWidths);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        table.setSpacingBefore(20f);//设置页边距
        table.setWidthPercentage(100);//设置表格宽度为100%
        return table;
    }

    /**
     * 报表表头 12列 8行
     * @param table
     */
    private void generatorHeader(PdfPTable table, String headerSuffix) {

        table.addCell(createPdfPCell("", 3, 1));
        table.addCell(createPdfPCell("บริษัท บลูเพย์ จำกัด",  5, 1, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("", 4, 1));

        table.addCell(createPdfPCell("", 3, 1));
        table.addCell(createPdfPCell("การรับชำระผ่านช่องทางอิเล็กทรอนิกส์",  5, 1, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("RP0001_" + headerSuffix, 4, 1));

        table.addCell(createPdfPCell("", 3, 1));
        table.addCell(createPdfPCell("รายงานการรับชำระประจำวัน",  5, 1, Element.ALIGN_CENTER));

        table.addCell(createPdfPCell("", 3, 1));
        table.addCell(createPdfPCell("Page 1 Of 1",  4, 1, Element.ALIGN_RIGHT));

        table.addCell(createPdfPCell("Location 0BBPW", 12, 1, Element.ALIGN_LEFT));

        table.addCell(createPdfPCell("ลำดับที่",  1, 3, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("เลขที่เอกสารยืนยันการทำรายการ",  1, 3, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("Account No. -  Invoice No.", 2,3, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("อั\u008Dตราภาษี", 1,3, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("มูลค่าสินค้า / บริการ ที่ต้องเสียภาษี",  5, 1, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("มูลค่าสินค้า / บริการ", 1,1, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("รวมทั้งสิ้น", 1, 3, Element.ALIGN_CENTER));

        table.addCell(createPdfPCell("ภาษี 7% ",  3, 1, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("ภาษี 0% ", 1 , 2, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("รวม",  1,2, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("ยกเว้นภาษี",  1,2, Element.ALIGN_CENTER));

        table.addCell(createPdfPCell("มูลค่าสุทธิ",  1,1, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("ภาษีมูลค่าเพิ่ม",  1, 1, Element.ALIGN_CENTER));
        table.addCell(createPdfPCell("รวม",  1,1, Element.ALIGN_CENTER));
    }

    private PdfPCell createPdfPCell(String text, int colSpan, int rowSpan) {
        return createPdfPCell(text, colSpan, rowSpan, Element.ALIGN_RIGHT);
    }

    private PdfPCell createPdfPCell(String text, int colSpan, int rowSpan, int align) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setColspan(colSpan);
        pdfPCell.setRowspan(rowSpan);
        pdfPCell.setPhrase(createParagraph(text));
        pdfPCell.setHorizontalAlignment(align);
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        pdfPCell.setMinimumHeight(30);//设置表格行高
        return pdfPCell;
    }

    private Paragraph createParagraph(String text) {
        Paragraph paragraph;
        paragraph = new Paragraph(text, font);
        paragraph.setAlignment(Phrase.ALIGN_CENTER);
        return paragraph;
    }
}
