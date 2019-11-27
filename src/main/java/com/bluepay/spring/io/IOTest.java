package com.bluepay.spring.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IOTest {
    private static final String mergeFilesDir = "D:\\lls\\chenping\\Documents\\My Pictures\\target";

    private static final String tempFileDir = "D:\\lls\\chenping\\Documents\\My Pictures\\temp\\";

    public static void main(String[] args) throws IOException {
        slice();
        merge();
    }

    /**
     * 文件切片
     */
    public static void slice() throws IOException {
        String targetFile = "D:\\lls\\chenping\\Documents\\My Pictures\\test.jpg";

        File file = new File(targetFile);
        System.out.println(file.exists());
        Long length = file.length();
        System.out.println(length);

        RandomAccessFile readFile = new RandomAccessFile(file, "rw");

        try {
            byte[] bytes = new byte[1024];
            int index = 0;
            int chunkSize;
            while ((chunkSize = readFile.read(bytes)) != -1){
                index ++;
                File tempFile = new File(tempFileDir + file.getName() + "_" + index);
                RandomAccessFile writeFile = null;
                try {
                    writeFile = new RandomAccessFile(tempFile, "rw");
                    writeFile.write(bytes, 0, chunkSize);
                } finally {
                    if (writeFile != null){
                        writeFile.close();
                    }
                }
            }
        } finally {
            if (readFile != null){
                readFile.close();
            }
        }
    }

    /**
     * 文件合并
     * @throws IOException
     */
    public static void merge() throws IOException {
        File dirFile = new File(tempFileDir);
        if (!dirFile.exists()) {
            throw new RuntimeException("文件不存在！");
        }
        String targetFileName = "test.jpg";
        //分片上传的文件已经位于同一个文件夹下，方便寻找和遍历
        String[] fileNames = dirFile.list();

        List<SequentialFile> files = new ArrayList<>();
        for (String fileName : fileNames) {
            int index = Integer.parseInt(fileName.substring(fileName.lastIndexOf(".jpg_") + 5));
            files.add(new SequentialFile(index, fileName));
        }
        Collections.sort(files);

        File targetFile = new File(mergeFilesDir, targetFileName);
        RandomAccessFile writeFile = new RandomAccessFile(targetFile, "rw");

        int position = 0;
        for (SequentialFile fileName : files) {
            File sourceFile = new File(tempFileDir, fileName.getFileName());
            RandomAccessFile readFile = new RandomAccessFile(sourceFile, "rw");
            int chunksize = 1024;
            byte[] buf = new byte[chunksize];
            writeFile.seek(position);
            int byteCount;
            while ((byteCount = readFile.read(buf)) != -1) {
                if (byteCount != chunksize) {
                    byte[] tempBytes = new byte[byteCount];
                    System.arraycopy(buf, 0, tempBytes, 0, byteCount);
                    buf = tempBytes;
                }
                writeFile.write(buf);
                position = position + byteCount;
            }
            readFile.close();
            FileUtils.deleteQuietly(sourceFile);
        }
        writeFile.close();
    }
}
