package com.ping.chen.spring.util;

import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ITextTest02 {
    public static void main(String[] args) throws Exception{
        //准备数
        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[]{"七月","1000","1100"});
        list.add(new String[]{"八月","950","1000"});
        list.add(new String[]{"九月","1000","1200"});
        //创建文档对象
        /**
         * 第一个参数：PageSize：设置纸张的大小：A1，A2，A3，A4(默认值),A5
         * 第2--5个参数：左右上下：纸张的边距
         */
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        //设置输出位置
        PdfWriter.getInstance(document, new FileOutputStream(new File("d://a.pdf")));
        //打开文档
        document.open();
        //写入内容
        //创建亚洲中文字体
        /**
         * 第一个参数：当前的字体：宋体、楷体...
         * 第二个参数：编码
         * 第三个参数：是否以内嵌的样式显示，值是boolean类型
         *        true：以内嵌的方式显示，比较占用资源
         *        false：不适用内嵌的方式显示，（更常用）
         */
        BaseFont baseFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont,
                AsianFontMapper.ChineseSimplifiedEncoding_H, BaseFont.NOT_EMBEDDED);
        /**********大标题的输出***********/
        //设置字体样式
        /**
         * 第一个参数：字体
         * 第二个参数：字体大小
         * 第三个参数：加粗、倾斜..
         * 第四个参数：颜色
         */
        Font bigTitleFont = new Font(baseFont, 30f, Font.BOLD, BaseColor.PINK);
        //设置内容
        /**
         * 第一个参数：内容
         * 第二个参数：字体格式
         */
        Paragraph bigTitleParagraph = new Paragraph("销量表", bigTitleFont);
        //设置对其方式
        bigTitleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        //添加至文档中
        document.add(bigTitleParagraph);
        /**********表格作者***********/
        //设置字体样式
        Font authorFont = new Font(baseFont, 20f, Font.NORMAL, BaseColor.BLACK);
        //设置内容
        Paragraph authorParagraph = new Paragraph("Ivon", authorFont);
        //设置对其方式
        authorParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
        //添加至文档中
        document.add(authorParagraph);
        /**********表格的创建***********/
        PdfPTable table = new PdfPTable(3);
        //设置当前面table的上边距
        table.setSpacingBefore(20f);

        /**********表格标题***********/
        //设置字体样式
        Font titleFont = new Font(baseFont, 15f, Font.BOLD, BaseColor.GREEN);
        //设置内容 参数：1.内容 2.字体样式
        table.addCell(new PdfPCell(new Phrase("月份", titleFont)));
        table.addCell(new PdfPCell(new Phrase("去年销量", titleFont)));
        table.addCell(new PdfPCell(new Phrase("今年销量", titleFont)));
        /**********表格内容***********/
        //设置字体样式
        Font contentFont = new Font(baseFont, 15f, Font.NORMAL, BaseColor.GREEN);
        //设置内容
        for(String[] values:list){
            table.addCell(new PdfPCell(new Phrase(values[0], contentFont)));
            table.addCell(new PdfPCell(new Phrase(values[1], contentFont)));
            table.addCell(new PdfPCell(new Phrase(values[2], contentFont)));
        }

        //将表格添加至document中
        document.add(table);

        //关闭文档
        document.close();
        System.out.println("输出完成");
    }
}
