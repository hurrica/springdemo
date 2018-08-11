package com.bluepay.spring.demo.io;


import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 算术表达式计算
 */
public class ExpressionDemo {
    private static Stack<String> postfixStack = new Stack<>();//后缀表达式栈
    private static Stack<String> operator = new Stack<>();//操作符栈

    private static boolean compareOperator(int topOp, int currentOp) {
        return topOp >= currentOp;
    }

    public static void main(String[] args) {
        String expression = "10*(2-2)+10*5-15-5*5";
        System.out.println(10*(2-2)+10*5-15-5*5);
        if (checkExpression(expression)){
            expression = expression.trim();
            List<String> list = stringToList(expression);
            toPostFixExpression(list);
            calculate();
        } else {
            System.out.println("错误的算术表达式：" + expression);
        }
    }

    private static void toPostFixExpression(List<String> list) {
        list.forEach(op->{
            OperatorEnum currentOp = OperatorEnum.valueOfOperator(op);
            if (Objects.nonNull(currentOp)){
                //操作符
                if (currentOp.isBracket){
                    //括号
                    if (currentOp.equals(OperatorEnum.LEFT_BRACKET)){
                        //左括号直接入操作符栈
                        operator.push(op);
                    } else {
                        // 右括号，轮询操作符栈，直到找到上一个左括号
                        while (!OperatorEnum.LEFT_BRACKET.getOperator().equals(operator.peek())){
                            postfixStack.push(operator.pop());
                        }
                        //左括号出栈
                        operator.pop();
                    }
                } else {
                    if (operator.empty()){
                        operator.push(op);
                    } else {
                        OperatorEnum topOp = OperatorEnum.valueOfOperator(operator.peek());
                        while (!operator.empty() && compareOperator(topOp.getPriority(), currentOp.getPriority()) && !topOp.isBracket){
                            topOp = OperatorEnum.valueOfOperator(operator.pop());
                            postfixStack.push(topOp.getOperator());
                        }
                        operator.push(currentOp.getOperator());
                    }
                }
            } else {
                //操作数直接入栈
                postfixStack.push(op);
            }
        });
        while (!operator.empty()){
            postfixStack.push(operator.pop());
        }
    }

    private static List<String> stringToList(String expression) {
        char[] chars = expression.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (char ch: chars) {
            if (Objects.nonNull(OperatorEnum.valueOfOperator(String.valueOf(ch)))){
                if (stringBuilder.length() > 0){
                    list.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
                list.add(String.valueOf(ch));
            }else {
                stringBuilder.append(ch);
            }
        }
        if (stringBuilder.length() > 0){
            list.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return list;
    }

    private static Stack<BigDecimal> operand = new Stack<>();
    private static void calculate() {
        Collections.reverse(postfixStack);//将后缀表达式反转
        String currentValue;//参与计算的算术运算符
        while (!postfixStack.empty()){
            currentValue = postfixStack.pop();
            if (Objects.nonNull(OperatorEnum.valueOfOperator(currentValue))){
                BigDecimal secondValue = operand.pop();
                BigDecimal firstValue = operand.pop();
                calculate(currentValue, secondValue, firstValue);
                operand.push(result);
            } else {
                operand.push(new BigDecimal(currentValue));
            }
        }
    }

    private static BigDecimal result;
    /**
     * 获取两个操作数并计算
     * @return
     */
    private static BigDecimal calculate(String operator, BigDecimal secondValue, BigDecimal firstValue) {
        switch (operator){
            case "+":
                result = firstValue.add(secondValue);
                return result;
            case "-":
                result = firstValue.subtract(secondValue);
                return result;
            case "*":
                result = firstValue.multiply(secondValue);
                return result;
            case "/":
                result = firstValue.divide(secondValue, 2, BigDecimal.ROUND_HALF_UP);
                return result;
            default:throw new RuntimeException("操作符错误！");
        }
    }

    private enum OperatorEnum{
        PLUS("+", 1, false),
        SUBTRACT("-", 1, false),
        MULTI("*", 2, false),
        DIVIDE("/", 2, false),
        LEFT_BRACKET("(", 3, true),
        RIGHT_BRACKET(")", 3, true);

        private String operator;
        private int priority;
        private boolean isBracket;

        OperatorEnum(String operator, int priority, boolean isBracket) {
            this.operator = operator;
            this.priority = priority;
            this.isBracket = isBracket;
        }

        public static OperatorEnum valueOfOperator(String op){
            Optional<OperatorEnum> optional = Arrays.stream(values()).filter(operatorEnum -> operatorEnum.operator.equals(op)).findAny();
            if (optional.isPresent()){
                return optional.get();
            }
            return null;
        }

        public String getOperator() {
            return operator;
        }

        public int getPriority() {
            return priority;
        }

        public boolean isBracket() {
            return isBracket;
        }
    }

    /**
     * 合法性校验
     * @param expression
     * @return
     */
    private static boolean checkExpression(String expression) {
        //去除空格
        expression = expression.replaceAll(" ","");

        Set<Character> operate_set = new HashSet<>();
        operate_set.add('-');
        operate_set.add('+');
        operate_set.add('*');
        operate_set.add('/');

        //拆分字符串
        char[] arr = expression.toCharArray();
        int len = arr.length;

        //前后括号计数，用来判断括号是否合法
        int checkNum=0;

        //数字集合
        List<Character> digit_list = new ArrayList<>();
        //循环判断
        for (int i = 0; i < len; i++) {
            if(Character.isDigit(arr[i])|| arr[i] == '.'){ //数字和小数点判断
                //把数字和小数点加入到集合中，为了下一步判断数字是否合法
                digit_list.add(arr[i]);
            }else { //非数字和小数点
                //如果集合中有值，取出来判断这个数字整体是否合法
                if(digit_list.size()>0) {
                    //判断字符串是否合法
                    boolean result = getNumberStringFromList(digit_list);
                    if(result){
                        //如果合法，清空集合，为了判断下一个
                        digit_list.clear();
                    }else{
                        //不合法，返回false
                        return false;
                    }
                }

                if (arr[i] == '+' || arr[i] == '*' || arr[i] == '/') {
                    //判断规则(1.不能位于首位 2.不能位于末尾 3.后面不能有其他运算符 4.后面不能有后括号)
                    if (i == 0 || i == (len - 1) || operate_set.contains(arr[i + 1]) || arr[i + 1] == ')') {
                        System.out.println("error type : '+' or '*' or '/' ->"+ arr[i]);
                        return false;
                    }
                } else if (arr[i] == '-') {
                    //减号判断规则(1.不能位于末尾 2.后面不能有其他运算符 3.后面不能有后括号)
                    if (i == (len - 1) || operate_set.contains(arr[i + 1]) || arr[i + 1] == ')') {
                        System.out.println("error type : '-' ->"+ arr[i]);
                        return false;
                    }

                } else if (arr[i] == '(') {
                    checkNum++;
                    //判断规则(1.不能位于末尾 2.后面不能有+，*，/运算符和后括号 3.前面不能为数字)
                    if (i == (len - 1) || arr[i + 1] == '+' || arr[i + 1] == '*' || arr[i + 1] == '/' || arr[i + 1] == ')'||(i != 0 && Character.isDigit(arr[i-1]))) {
                        System.out.println("error type : '(' ->"+ arr[i]);
                        return false;
                    }
                } else if (arr[i] == ')') {
                    checkNum--;
                    //判定规则(1.不能位于首位 2.后面不能是前括号 3.括号计数不能小于0，小于0说明前面少了前括号)
                    if (i == 0 || (i < (len - 1) && arr[i + 1] == '(') || checkNum < 0) {
                        System.out.println("error type : ')' ->"+ arr[i]);
                        return false;
                    }
                }else{
                    //非数字和运算符
                    System.out.println("error type : not number and operator ->"+ arr[i]);
                    return false;
                }
            }
        }

        //如果集合中有值，取出来判断这个数字整体是否合法
        if(digit_list.size()>0) {
            //判断字符串是否合法
            boolean result = getNumberStringFromList(digit_list);
            if(result){
                //如果合法，清空集合，为了判断下一个
                digit_list.clear();
            }else{
                //不合法，返回false
                return false;
            }
        }

        //不为0,说明括号不对等，可能多前括号
        if(checkNum!=0){
            return false;
        }
        return true;
    }

    private static boolean getNumberStringFromList(List<Character> list){
        //如果集合中有值，取出来判断这个数字整体是否合法
        if(list != null){
            StringBuffer stringBuffer = new StringBuffer();
            for(Character character : list){
                stringBuffer.append(character);
            }
            //正则判断数字是否合法
            boolean result = isNumber(stringBuffer.toString());

            if(!result){
                System.out.println("error type: digit ->"+stringBuffer.toString());
            }
            return result;
        }
        return false;
    }

    private static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^-?([1-9]\\d*\\.\\d+|0\\.\\d*[1-9]\\d*|[1-9]\\d*|0)$");
        Matcher isNum = pattern.matcher(str);
        return !isNum.matches();
    }
}
