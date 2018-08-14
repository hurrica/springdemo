package com.bluepay.spring.demo.thread.file;

import java.io.*;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ping.chen on 2018/8/11.
 */
public class MyFileReader {
    transient int coreThread=10;
    private ExecutorService execute = Executors.newFixedThreadPool(coreThread);
    private final LinkedBlockingQueue<FileData> queue = new LinkedBlockingQueue<>();
    private ConcurrentHashMap<String, FileData> minValueMap = new ConcurrentHashMap<>();
    private  List<FileData> dataList = new LinkedList<>();

    public static void main(String[] args) {
        String directory = "D:/test";
        // 读取文件
        MyFileReader myFileReader = new MyFileReader();
        myFileReader.readFile(directory);

        //排序
        Thread sortThread = myFileReader.new ConsumerThread();
        sortThread.start();

        FindDataThread findDataThread = myFileReader.new FindDataThread();
        findDataThread.start();

    }

    private class FindDataThread extends Thread{
        @Override
        public void run(){
            int count =0;
            while (true){
                try {
                    if (count != dataList.size()){
                        System.out.println("--------------------------------------------------------------------------------------");
                        dataList.forEach(data -> System.out.println(data));
                        System.out.println("--------------------------------------------------------------------------------------");
                        count = dataList.size();
                    }
                    Thread.sleep(1000);
                    System.out.println("--------------------------------    我是分割线    -----------------------------------");
                    System.out.println(dataList.size());
                    Enumeration enumeration = minValueMap.elements();
                    while (enumeration.hasMoreElements()){
                        System.out.println(enumeration.nextElement());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ConsumerThread extends Thread{
        @Override
        public void run() {
            FileData fileData;
            while (true){
                try {
                    fileData = queue.take();
                    sortAndAdd(fileData);//放入队列并排序
                    findMinValue(fileData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查找最小值
     * @param fileData
     */
    private void findMinValue(FileData fileData) {
        FileData minValue = minValueMap.get(fileData.getGroupId());
        if (Objects.isNull(minValue)){
            minValueMap.put(fileData.getGroupId(), fileData);
        } else if (minValue.compareTo(fileData) >= 0){
            minValueMap.put(fileData.getGroupId(), fileData);
        }
    }

    /**
     * 排序
     * @param fileData
     */
    private void sortAndAdd(FileData fileData) {
        int index = binarySearch(dataList, fileData);
        dataList.add(index, fileData);
    }

    /**
     * 二分法查找
     */
    public static int binarySearch(List<FileData> dataList, FileData currentData){
        return binarySearch(dataList, currentData, 0, dataList.size());
    }

    private static int binarySearch(List<FileData> dataList, FileData currentData, int left, int right) {
        int high = right-1;
        while (left<=high){
            int mid = (left + high)/2;
            FileData temp = dataList.get(mid);
            if (temp.compareTo(currentData) > 0)
                high = mid-1;
            else if (temp.compareTo(currentData) < 0)
                left = (mid+1);
            else
                return mid;
        }
        return dataList.size();
    }

    private class Reader implements Runnable{
        private String filePath;
        public Reader(String filePath){
            this.filePath = filePath;
        }
        @Override
        public void run() {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(filePath));
                try {
                    do {
                        String content = bufferedReader.readLine();
                        FileData data = resolveData(content);
                        queue.add(data);//放入到队列
                    } while (bufferedReader.read() != -1);
                } catch (IOException e) {
                    System.err.println("文件错误：" + e.getCause());
                }
            } catch (FileNotFoundException e) {
                System.err.println("未找到该文件：" + filePath);
            } finally {
                if (bufferedReader != null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    private void readFile(String directory) {
        File file = new File(directory);
        if (file.isDirectory()){
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                String filePath = directory + "/" + files[i];
                execute.execute(new Reader(filePath));
            }
        } else {
            System.out.println("路径错误！");
        }
    }

    /**
     * 解析文件内容
     * @param content
     * @return
     */
    private FileData resolveData(String content) {
        String[] contents = content.split(",");
        return new FileData(contents);
    }

}
