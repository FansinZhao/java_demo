package com.fansin.io;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by zhaofeng on 17-4-25.
 */
public class UtilDemo {
    public static void main(String[] args) {
        System.out.println("jdk示例");
        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder, Locale.CHINA);
        System.out.println("1 重排序");
        formatter.format("%4$2s %3$2s %2$2s %1$2s","a","b","c","d");
        System.out.println("System.out 本身就是PrintStream");
        System.out.format("%4$2s %3$2s %2$2s %1$2s","aaaa","bbbb","cccc","dddd");
        System.out.println("\n------------------");
        System.out.println(formatter.toString());
        System.out.println("2 指定数据风格");
        System.out.format("%(,.3f",123456789.456);
        Calendar c = Calendar.getInstance();
        System.out.format("\nDuke's Birthday:%1$TH:%1$TM:%1$TS %1$tm %1$te,%1$tY\n", c);
        System.out.format("boolean: %b\n",null);
        System.out.format("Boolean: %B\n",1);
        System.out.format("boolean: %b\n",false);
        System.out.format("hex String: %h\n",null);
        System.out.format("hex string: %h\n",15);
        System.out.format("String: %s\n",null);
        System.out.format("String: %s\n","aaa");
        System.out.format("String: %S\n","aaa");
        System.out.format("char: %c\n",null);
        System.out.format("char: %c\n",'a');
        System.out.format("char: %C\n",'a');
//        System.out.format("String: %C\n","a");
        System.out.format("十进制: %d\n",null);
        System.out.format("十进制: %d\n",20);
//        System.out.format("int: %D\n",20);
//        System.out.format("int: %d\n","20");
        System.out.format("八进制: %o\n",null);
        System.out.format("八进制: %o\n",9);
//        System.out.format("八进制: %O\n",9);
        System.out.format("十六进制: %x\n",null);
        System.out.format("十六进制: %x\n",15);
        System.out.format("十六进制: %X\n",15);
        System.out.format("科学计数法: %e\n",null);
        System.out.format("科学计数法: %e\n",0000100000e2);
        System.out.format("科学计数法: %E\n",100000e2);
//        System.out.format("科学计数法: %E\n",100000);
        System.out.format("浮点: %f\n",null);
        System.out.format("浮点: %f\n",100000.2);
        System.out.format("浮点: %.2f\n",100000.2);
        System.out.format("带精度科学计数法: %.4g\n",null);
        System.out.format("带精度科学计数法: %.4g\n",0000101000.2);
        System.out.format("带精度科学计数法: %.4G\n",101000.2);
        System.out.format("带精度十六进制浮点???: %.4a\n",15.15);
        System.out.format("带精度十六进制浮点???: %.4A\n",15.15);
        System.out.format("日期 月份: %TB\n",c);
        System.out.format("日期 月份: %Tb\n",new Date());
        System.out.format("日期 月份: %Th\n",c);
        System.out.format("日期 星期: %TA\n",c);
        System.out.format("日期 星期: %Ta\n",c);
        System.out.format("日期 年份前两位?: %TC\n",c);
        System.out.format("日期 年份后两位: %Ty\n",c);
        System.out.format("日期 四位年份: %TY\n",c);
        System.out.format("日期 一年中的天数: %Tj\n",c);
        System.out.format("日期 月份: %Tm\n",c);
        System.out.format("日期 一个月天数: %Td\n",c);
        System.out.format("日期 一个月天数不带0: %Te\n",c);
        System.out.format("时间 24小时 %tH\n",c);
        System.out.format("时间 24小时不带0 %tk\n",c);
        System.out.format("时间 12小时 %tI\n",c);
        System.out.format("时间 12小时不带0 %tl\n",c);
        System.out.format("时间 分钟 %tM\n",c);
        System.out.format("时间 秒 %tS\n",c);
        System.out.format("时间 毫秒 %tL\n",c);
        System.out.format("时间 毫微秒 %tN\n",c);
        System.out.format("时间 am/pm %tp\n",c);
        System.out.format("时间 am/pm %Tp\n",c);
        System.out.format("时间 时区 %tZ\n",c);
        System.out.format("时间日期组合 24小时 %tR\n",c);
        System.out.format("时间日期组合 24小时 %TR\n",c);
        System.out.format("时间日期组合 24小时 %TT\n",c);
        System.out.format("时间日期组合 24小时 %tT\n",c);
        System.out.format("时间日期组合 12小时 %Tr\n",c);
        System.out.format("时间日期组合 日期 %TD\n",c);
        System.out.format("时间日期组合 日期 %TF\n",c);
        System.out.format("时间日期组合 日期时间 %Tc\n",c);
        System.out.format("flag  左对齐无效?:%s\n","        dddddddddd   ");
        System.out.format("flag  左对齐无效?:%-10s\n","         dddddddddd   ");
        System.out.format("flag  在八进制和十六进制前面添加0或者0x替换形式:%#o %#x\n",7,15);
        System.out.format("flag  只能整数和浮点显示符号:%+d\n",15);
        System.out.format("flag  只能整数和浮点显示符号:%+.2f\n",-15.25);
        System.out.format("flag  正值,左面添加空格:% .2f\n",15.25);
        System.out.format("flag  正值,左面添加空格:% .2f\n",-15.25);
        System.out.format("flag  结果用0填充,浮点在右边填充,:%06f\n",1525.2);
        System.out.format("flag  结果用0填充,整数在左边填充,:%06d\n",15252);
        System.out.format("flag  结果使用会计计算,小数部分不做分割:%0,6f\n",1525.222);
        System.out.format("flag  结果为负数则用括号括起来:%(6f\n",1525.222);
        System.out.format("flag  结果为负数则用括号括起来:%(6f\n",-1525.222);
        System.out.format("flag  混合使用:%0,+6f\n",1525.222);
        System.out.format("flag  混合使用顺序无关:%,+06f\n",1525.222);








    }
}
