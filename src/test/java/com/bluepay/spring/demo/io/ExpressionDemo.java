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

    /**
     * 中缀表达式转换成后缀表达式
     * @param expression
     */
    private static void toPostFixExpression(char[] expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length; i++) {
            //1、从右到左扫描表达式
            char ch = expression[i];
            OperatorEnum op = OperatorEnum.valueOfOperator(String.valueOf(ch));
            if (Objects.nonNull(op)){//如果是运算符
                if (sb.length() > 0){
                    postfixStack.push(sb.toString());
                    sb.setLength(0);
                }
                if (op.isBracket){//括号
                    if (op.equals(OperatorEnum.LEFT_BRACKET)){//左括号，直接放入操作符栈
                        operator.push(op.operator);
                    } else { //右括号
                        while (!operator.peek().equals(OperatorEnum.LEFT_BRACKET.operator)){
                            postfixStack.push(String.valueOf(operator.pop()));
                        }
                        operator.pop();
                    }
                } else {//不是括号比较优先级
                    if (operator.empty()){
                        //操作符栈为空，直接入栈
                        operator.push(op.operator);
                    } else {
                        while (!operator.empty()){
                            String top = operator.peek();//栈顶元素
                            if (compareOperator(OperatorEnum.valueOfOperator(top).priority, op.priority) && !OperatorEnum.valueOfOperator(top).isBracket){
                                postfixStack.push(String.valueOf(operator.pop()));
                            } else {
                                break;
                            }
                        }
                        //操作数入栈
                        operator.push(op.operator);
                    }
                }
            } else {//操作数放入操作数栈
                sb.append(ch);
            }
        }
        while (!operator.empty()){
            postfixStack.push(String.valueOf(operator.pop()));
        }
        if (sb.length() > 0){
            postfixStack.push(sb.toString());
        }
    }

    private static boolean compareOperator(int priority, int priority1) {
        return priority > priority1;
    }

    public static void main(String[] args) {
        String expression = "5+6*10-15+(5-5)*1000";
        System.out.println(expression);
        if (checkExpression(expression)){
            expression = expression.trim();
            char[] chars = expression.toCharArray();
            //拆分表达式
            toPostFixExpression(chars);
            //计算
            while (!postfixStack.empty()){
                System.out.print(postfixStack.pop());
            }

            calculate();
            System.out.println(result);
        } else {
            System.out.println("错误的算术表达式：" + expression);
        }
    }

    private static Stack<BigDecimal> operand = new Stack<>();
    private static void calculate() {
        Collections.reverse(postfixStack);
        while (!postfixStack.empty()){
            String value = postfixStack.pop();
            OperatorEnum operatorEnum = OperatorEnum.valueOfOperator(value);
            if (Objects.nonNull(operatorEnum)){
                calculate(operatorEnum);
            } else {
                operand.push(new BigDecimal(value));
            }
        }
    }

    private static BigDecimal result;
    /**
     * 获取两个操作数并计算
     * @return
     */
    private static void calculate(OperatorEnum op) {
        switch (op.operator){
            case "+":
                result = operand.pop().add(operand.pop());
                break;
            case "-":
                result = operand.pop().subtract(operand.pop());
                break;
            case "*":
                result = operand.pop().multiply(operand.pop());
                break;
            case "/":
                result = operand.pop().divide(operand.pop(), 2, BigDecimal.ROUND_HALF_UP);
                break;
        }
        operand.push(result);
    }

    private static boolean isOperator(char operator) {
        return Objects.nonNull(OperatorEnum.valueOfOperator(String.valueOf(operator)));
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
        StringBuffer stringBuffer = new StringBuffer();
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
        if( !isNum.matches() ){ return false; } return true;
    }
}
