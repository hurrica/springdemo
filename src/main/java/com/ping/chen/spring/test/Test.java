package com.ping.chen.spring.test;

import com.ping.chen.spring.util.DateUtil;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author chenping
 * @Description test
 * @Date 2019/3/11
 **/
public class Test {
    private String field;


    public static String seq(){
        Long current = System.currentTimeMillis();
        int a = (int) (Math.random() * 1000000);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
        return (current + "" + String.format("%0" + 6 + "d", a));
    }

    public static void seq(int length, int num) {
        System.out.println("循环" + num + "次");
        Long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            String seq = seq();
            if (seq.length() != 19){
                System.out.println(seq);
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static Date getAfterDayTime(int day, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, day);
        return calendar.getTime();
    }

    private static boolean check(String e) {
        if (e.equals("ss")){
            throw new RuntimeException("错误");
        }
        return true;
    }


    public static void main(String[] args) throws IOException, IllegalAccessException, ParseException {
        TestInterface testInterface = (TestInterface) Proxy.newProxyInstance(TestInterface.class.getClassLoader(), new Class[]{TestInterface.class}, new MyInvocationHandler(new TestInterfaceImpl()));

        testInterface.getInstance();
    }



    public static BigDecimal calCashServiceFee(Date applyDate, Date endDate, BigDecimal feeRate, BigDecimal feeAmt) {
        Long dayBetween = DateUtil.getBetweenDay(applyDate, endDate);


        if (feeRate.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }

        //保留两位小数
        BigDecimal FeeAmt = feeAmt
                .multiply(feeRate).divide(new BigDecimal(100), 10, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(dayBetween)).divide(new BigDecimal(360), 10, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
        return FeeAmt;
    }

    private static BigDecimal getNormalCashFeeAmount(Long dayBetween, BigDecimal cashAmt, BigDecimal cashRate) {
        //保存支付流水(保留两位小数)
        BigDecimal cashFeeAmt = cashAmt
                .multiply(cashRate).divide(new BigDecimal(100), 10, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(dayBetween)).divide(new BigDecimal(360), 10, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
        return cashFeeAmt;
    }


    private static final BigDecimal YEAR = new BigDecimal("360");

    private static final int SCALE = 2;

    private static final BigDecimal PERCENT = new BigDecimal("100");

    private static final BigDecimal HALF_YEAR = new BigDecimal("180");

    /**
     * 提前结清计算折扣费用
     * @param amount
     * @param billFeeRate 票据段折扣率
     * @param certificateFeeRate 凭证段折扣率
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    public static BigDecimal calEarlySettlementDiscountFee(BigDecimal amount, BigDecimal billFeeRate, BigDecimal certificateFeeRate, LocalDate startDate, LocalDate endDate) {
        return amount
                .multiply(certificateFeeRate.divide(PERCENT, SCALE, BigDecimal.ROUND_HALF_UP))
                .multiply(BigDecimal.valueOf(DateUtil.daysBetween(startDate, endDate)))
                .divide(YEAR, SCALE, BigDecimal.ROUND_HALF_UP)
                .add(
                        amount.multiply(billFeeRate.divide(PERCENT, SCALE, BigDecimal.ROUND_HALF_UP))
                                .multiply(HALF_YEAR)
                                .divide(YEAR, SCALE, BigDecimal.ROUND_HALF_UP)
                );
    }
}
