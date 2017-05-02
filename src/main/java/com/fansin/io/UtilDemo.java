package com.fansin.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.*;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.regex.MatchResult;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
        System.out.format("百分比 和换行n: %1$d%%%n",85);
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


        System.out.println("-----------RandomAccessFile------------");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("randomAccessFile", "rw")
        ) {
            System.out.println("文件模式有:r,rw,rws,rwd");
            System.out.println("这个类既可以写又可以读,可以进根据直接写入读取java类型,字段使用unicode,null占用2个字节");
            System.out.println("可以写入的数据类型:null,boolean,byte,bytes,short,char,chars,int,long,float,double,utf");
//            randomAccessFile.writeUTF(null);
//            System.out.println("null的大小:"+randomAccessFile.getChannel().size());
//            System.out.println("null的大小:"+randomAccessFile.length());
            randomAccessFile.writeBoolean(true);//boolean 1
            randomAccessFile.writeByte(0);//byte 1
            randomAccessFile.writeBytes("byte");//字节数组 不能中文,中文使用utf或者 write(byte[])
            randomAccessFile.writeShort(1);//byte 2
            randomAccessFile.writeChar('q');//char 2
            randomAccessFile.writeChars("abc");//chars 2n
            randomAccessFile.writeInt(2);//int 4
            randomAccessFile.writeLong(3);//long 8
            randomAccessFile.writeFloat(4.444f);//long 4
            randomAccessFile.writeDouble(5.5555d);//double 8
            randomAccessFile.writeUTF("中二");
            System.out.println("总长度:"+randomAccessFile.length());
            System.out.println("依次读取内容");
//            byte bs[] =new byte[2];
//            randomAccessFile.read(bs);
//            System.out.println("获取null:"+new String(bs));
            System.out.println("seek 将指针定位到0位置");
            System.out.println("文件指针:"+randomAccessFile.getFilePointer());
            randomAccessFile.seek(0);
            System.out.println("文件指针:"+randomAccessFile.getFilePointer());
            System.out.println("获取boolean:"+randomAccessFile.readBoolean());
            System.out.println("获取byte:"+randomAccessFile.readByte());
            byte bs[] = new byte[4];//1个中文=6字节
            int length = randomAccessFile.read(bs,0,4);
            System.out.println("获取bytes:"+new String(bs,"UTF-8")+" "+length);
            System.out.println("获取short:"+randomAccessFile.readShort());
            System.out.println("获取char:"+randomAccessFile.readChar());
            System.out.println("获取chars:"+randomAccessFile.readChar());
            System.out.println("获取chars:"+randomAccessFile.readChar());
            System.out.println("获取chars:"+randomAccessFile.readChar());
            System.out.println("获取 int:"+randomAccessFile.readInt());
            System.out.println("获取 long:"+randomAccessFile.readLong());
            System.out.println("获取 float:"+randomAccessFile.readFloat());
            System.out.println("获取 double:"+randomAccessFile.readDouble());
            System.out.println("获取 utf:"+randomAccessFile.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------Scanner------------");
        String content = "true 127 1 123 222 33.3 5555 3.444\n" +
                "naaaaa";
        try (Scanner scanner = new Scanner(content)) {
            System.out.println("默认分割符:[" + scanner.delimiter() + "]");
            System.out.println(scanner.hasNextBoolean() + " boolean:" + scanner.nextBoolean());
            System.out.println(scanner.hasNextByte() + " byte:" + scanner.nextByte());
            System.out.println(scanner.hasNextShort() + " short:" + scanner.nextShort());
            System.out.println(scanner.hasNextInt() + " int:" + scanner.nextInt());
            System.out.println(scanner.hasNextLong() + " long:" + scanner.nextLong());
            System.out.println(scanner.hasNextDouble() + " double:" + scanner.nextDouble());
            System.out.println(scanner.hasNextBigInteger() + " bigInteger:" + scanner.nextBigInteger());
            System.out.println(scanner.hasNextBigDecimal() + " bigDecimal:" + scanner.nextBigDecimal());
            System.out.println("?下一行:" + scanner.nextLine());
        }
        String text = "a|sb|sc|sd|se";
        try (Scanner scanner = new Scanner(text)) {
            scanner.findInLine("(\\w+)\\|s(\\p{Alpha}+)\\|s(\\p{Alpha}+)\\|s(\\p{Alpha}+)\\|s(\\p{Alpha}+)");
            MatchResult result = scanner.match();
            System.out.println("使用正则匹配分隔符");
            for (int i = 0; i <=result.groupCount(); i++) {
                System.out.println(result.group(i));
            }
        }
        try (Scanner scanner = new Scanner(text)) {
            scanner.findWithinHorizon("(\\w+)\\|s(\\p{Alpha}+)\\|s(\\p{Alpha}+)\\|s(\\p{Alpha}+)\\|s(\\p{Alpha}+)",0);
            MatchResult result = scanner.match();
            System.out.println("使用正则匹配分隔符");
            for (int i = 0; i <=result.groupCount(); i++) {
                System.out.println(result.group(i));
            }
        }
        System.out.println("使用自定义分隔符!");
        try (Scanner scanner = new Scanner(text).useDelimiter("\\|s")) {
            System.out.println(scanner.next());
            System.out.println(scanner.next());
            System.out.println(scanner.next());
        }


        System.out.println("-----------ZipFile------------");
        String zipfile = "中文.zip";
        try (ZipFile zipFile = new ZipFile(zipfile)) {
            ZipEntry zipEntry = zipFile.getEntry("zipfile0");
            System.out.println("读取文件信息:"+zipEntry.getName()+" "+zipEntry.getSize());
            InputStream in = zipFile.getInputStream(zipEntry);
            byte b[] = new byte[1024];
            in.read(b);
            System.out.println("读取zip其中一个文件的内容:\n"+new String(b));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------ZipFile/JarFile------------");
        try (JarFile jarFile = new JarFile("demo-0.0.1-SNAPSHOT.jar")
        ) {
            Manifest manifest = jarFile.getManifest();
            System.out.println("获取manifest清单:"+manifest.getMainAttributes().getValue("Build-Jdk"));
            System.out.println("获取manifest清单:"+manifest.getMainAttributes().getValue("Built-By"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
class BigDecimalDemo{
    public static void main(String[] args) {
        BigDecimal bigDecimal6 =new BigDecimal("1.6");
        BigDecimal bigDecimal5 =new BigDecimal("1.5");
        BigDecimal bigDecimal1 =new BigDecimal("1.1");
        BigDecimal bigDecimal0 =new BigDecimal("1.0");
        BigDecimal bigDecimal_0 =new BigDecimal("-1.0");
        BigDecimal bigDecimal_1 =new BigDecimal("-1.1");
        BigDecimal bigDecimal_5 =new BigDecimal("-1.5");
        BigDecimal bigDecimal_6 =new BigDecimal("-1.6");

        /*
        *
        BigDecimal bigDecimal6 =new BigDecimal("2.6");
        BigDecimal bigDecimal5 =new BigDecimal("2.5");
        BigDecimal bigDecimal1 =new BigDecimal("2.1");
        BigDecimal bigDecimal0 =new BigDecimal("2.0");
        BigDecimal bigDecimal_0 =new BigDecimal("-2.0");
        BigDecimal bigDecimal_1 =new BigDecimal("-2.1");
        BigDecimal bigDecimal_5 =new BigDecimal("-2.5");
        BigDecimal bigDecimal_6 =new BigDecimal("-2.6");
        *
        * */


        System.out.println("--------------ROUND_UP 远离0-------------");
        System.out.println(bigDecimal6 + " ROUND_UP: "+bigDecimal6.setScale(0,BigDecimal.ROUND_UP));
        System.out.println(bigDecimal5 + " ROUND_UP: "+bigDecimal5.setScale(0,BigDecimal.ROUND_UP));
        System.out.println(bigDecimal1 + " ROUND_UP: "+bigDecimal1.setScale(0,BigDecimal.ROUND_UP));
        System.out.println(bigDecimal0 + " ROUND_UP: "+bigDecimal0.setScale(0,BigDecimal.ROUND_UP));
        System.out.println(bigDecimal_0 + " ROUND_UP: "+bigDecimal_0.setScale(0,BigDecimal.ROUND_UP));
        System.out.println(bigDecimal_1 + " ROUND_UP: "+bigDecimal_1.setScale(0,BigDecimal.ROUND_UP));
        System.out.println(bigDecimal_5 + " ROUND_UP: "+bigDecimal_5.setScale(0,BigDecimal.ROUND_UP));
        System.out.println(bigDecimal_6 + " ROUND_UP: "+bigDecimal_6.setScale(0,BigDecimal.ROUND_UP));
        System.out.println("--------------ROUND_DOWN 靠近0-------------");
        System.out.println(bigDecimal6 + " ROUND_DOWN: "+bigDecimal6.setScale(0,BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal5 + " ROUND_DOWN: "+bigDecimal5.setScale(0,BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal1 + " ROUND_DOWN: "+bigDecimal1.setScale(0,BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal0 + " ROUND_DOWN: "+bigDecimal0.setScale(0,BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal_0 + " ROUND_DOWN: "+bigDecimal_0.setScale(0,BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal_1 + " ROUND_DOWN: "+bigDecimal_1.setScale(0,BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal_5 + " ROUND_DOWN: "+bigDecimal_5.setScale(0,BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal_6 + " ROUND_DOWN: "+bigDecimal_6.setScale(0,BigDecimal.ROUND_DOWN));
        System.out.println("-------------ROUND_CEILING 靠近正无穷大--------------");
        System.out.println(bigDecimal6 + " ROUND_CEILING: "+bigDecimal6.setScale(0,BigDecimal.ROUND_CEILING));
        System.out.println(bigDecimal5 + " ROUND_CEILING: "+bigDecimal5.setScale(0,BigDecimal.ROUND_CEILING));
        System.out.println(bigDecimal1 + " ROUND_CEILING: "+bigDecimal1.setScale(0,BigDecimal.ROUND_CEILING));
        System.out.println(bigDecimal0 + " ROUND_CEILING: "+bigDecimal0.setScale(0,BigDecimal.ROUND_CEILING));
        System.out.println(bigDecimal_0 + " ROUND_CEILING: "+bigDecimal_0.setScale(0,BigDecimal.ROUND_CEILING));
        System.out.println(bigDecimal_1 + " ROUND_CEILING: "+bigDecimal_1.setScale(0,BigDecimal.ROUND_CEILING));
        System.out.println(bigDecimal_5 + " ROUND_CEILING: "+bigDecimal_5.setScale(0,BigDecimal.ROUND_CEILING));
        System.out.println(bigDecimal_6 + " ROUND_CEILING: "+bigDecimal_6.setScale(0,BigDecimal.ROUND_CEILING));

        System.out.println("--------------ROUND_FLOOR 靠近负无穷大-------------");
        System.out.println(bigDecimal6 + " ROUND_FLOOR: "+bigDecimal6.setScale(0,BigDecimal.ROUND_FLOOR));
        System.out.println(bigDecimal5 + " ROUND_FLOOR: "+bigDecimal5.setScale(0,BigDecimal.ROUND_FLOOR));
        System.out.println(bigDecimal1 + " ROUND_FLOOR: "+bigDecimal1.setScale(0,BigDecimal.ROUND_FLOOR));
        System.out.println(bigDecimal0 + " ROUND_FLOOR: "+bigDecimal0.setScale(0,BigDecimal.ROUND_FLOOR));
        System.out.println(bigDecimal_0 + " ROUND_FLOOR: "+bigDecimal_0.setScale(0,BigDecimal.ROUND_FLOOR));
        System.out.println(bigDecimal_1 + " ROUND_FLOOR: "+bigDecimal_1.setScale(0,BigDecimal.ROUND_FLOOR));
        System.out.println(bigDecimal_5 + " ROUND_FLOOR: "+bigDecimal_5.setScale(0,BigDecimal.ROUND_FLOOR));
        System.out.println(bigDecimal_6 + " ROUND_FLOOR: "+bigDecimal_6.setScale(0,BigDecimal.ROUND_FLOOR));

        System.out.println("--------------ROUND_HALF_UP -------------");
        System.out.println(bigDecimal6 + " ROUND_HALF_UP: "+bigDecimal6.setScale(0,BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal5 + " ROUND_HALF_UP: "+bigDecimal5.setScale(0,BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal1 + " ROUND_HALF_UP: "+bigDecimal1.setScale(0,BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal0 + " ROUND_HALF_UP: "+bigDecimal0.setScale(0,BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal_0 + " ROUND_HALF_UP: "+bigDecimal_0.setScale(0,BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal_1 + " ROUND_HALF_UP: "+bigDecimal_1.setScale(0,BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal_5 + " ROUND_HALF_UP: "+bigDecimal_5.setScale(0,BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal_6 + " ROUND_HALF_UP: "+bigDecimal_6.setScale(0,BigDecimal.ROUND_HALF_UP));

        System.out.println("--------------ROUND_HALF_EVEN -------------");
        System.out.println(bigDecimal6 + " ROUND_HALF_EVEN: "+bigDecimal6.setScale(0,BigDecimal.ROUND_HALF_EVEN));
        System.out.println(bigDecimal5 + " ROUND_HALF_EVEN: "+bigDecimal5.setScale(0,BigDecimal.ROUND_HALF_EVEN));
        System.out.println(bigDecimal1 + " ROUND_HALF_EVEN: "+bigDecimal1.setScale(0,BigDecimal.ROUND_HALF_EVEN));
        System.out.println(bigDecimal0 + " ROUND_HALF_EVEN: "+bigDecimal0.setScale(0,BigDecimal.ROUND_HALF_EVEN));
        System.out.println(bigDecimal_0 + " ROUND_HALF_EVEN: "+bigDecimal_0.setScale(0,BigDecimal.ROUND_HALF_EVEN));
        System.out.println(bigDecimal_1 + " ROUND_HALF_EVEN: "+bigDecimal_1.setScale(0,BigDecimal.ROUND_HALF_EVEN));
        System.out.println(bigDecimal_5 + " ROUND_HALF_EVEN: "+bigDecimal_5.setScale(0,BigDecimal.ROUND_HALF_EVEN));
        System.out.println(bigDecimal_6 + " ROUND_HALF_EVEN: "+bigDecimal_6.setScale(0,BigDecimal.ROUND_HALF_EVEN));

    }
}
