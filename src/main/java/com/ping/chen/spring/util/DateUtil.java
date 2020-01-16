package com.ping.chen.spring.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String dateFormatString(Date date, String pattern){
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date stringToDate(String date, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    public static Date longToDate(Long millis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
    /**
     * 获取当前月份的第一天的日期
     */
    public static Date firstDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月最后一天的日期
     */
    public static Date lastDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        try {
            exeCmd("cmd /c time 15:30:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dateFormatString(longToDate(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss"));
    }


    public static void exeCmd(String commandStr) throws Exception {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static long daysBetween(LocalDate localDate1, LocalDate localDate2) {
        return localDate1.until(localDate2, ChronoUnit.DAYS);
    }

    public static LocalDate toLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parse(String dateStr, String pattern) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(TemporalAccessor temporal, String pattern) {
        if (temporal == null){
            return null;
        }
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    public static long getBetweenDay(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        start.set(11, 0);
        start.set(12, 0);
        start.set(13, 0);
        start.set(14, 0);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.set(11, 0);
        end.set(12, 0);
        end.set(13, 0);
        end.set(14, 0);
        long n = end.getTimeInMillis() - start.getTimeInMillis();
        Long day = n / 86400000L;
        return day;
    }
}
