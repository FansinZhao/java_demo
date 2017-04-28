package com.fansin.io;

import sun.security.action.GetPropertyAction;

import java.io.*;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by zhaofeng on 17-4-25.
 */
public class ReaderAndWriterDemo {

    public static void main(String[] args) {
        System.out.println("------------OutputStreamWriter/InputStreamReader 或 FileWriter/FileReader--------------");
        String outWriter = "outWriter";

        try (
                /*
                FileOutputStream fileOutputStream = new FileOutputStream(outWriter);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)
                */
                FileWriter outputStreamWriter= new FileWriter(outWriter)
        ) {

            System.out.println("FileWriter 简化代码量 等价 \n" +
                    "FileOutputStream fileOutputStream = new FileOutputStream(outWriter);\n" +
                    "OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)");

            System.out.println("OutputStreamWriter 字符集:"+outputStreamWriter.getEncoding());
            outputStreamWriter.write('a');
            outputStreamWriter.write('\n');
            outputStreamWriter.write("a中文bc\n".toCharArray());
            outputStreamWriter.write("这是一条中文信息!");
            outputStreamWriter.flush();//手动刷新
            System.out.println("写入字符文件成功!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
              /*
                FileInputStream fileInputStream = new FileInputStream(outWriter);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream)
                */
              FileReader inputStreamReader = new FileReader(outWriter)
        ) {
            System.out.println("FileReader 简化代码量 等价\n" +
                    "FileInputStream fileInputStream = new FileInputStream(outWriter);\n" +
                    "InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream)");
            System.out.println("char[] 可以取出任意一个字符,包括中文字符");
            System.out.println("InputStreamReader 字符集:"+inputStreamReader.getEncoding());
            System.out.println("字节流解码器是否准备好了?"+inputStreamReader.ready());
            char[] chars = new char[1024];
            System.out.println("打印字符!");
            while (inputStreamReader.read(chars) != -1){
                System.out.println(new String(chars));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------------BufferedWriter/BufferedReader  --------------");
        String bufferFile = "bufferWriter";

        try (
                FileWriter fileWriter = new FileWriter(bufferFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            System.out.println("BufferedWriter写入文件,添加换行功能!");
            System.out.println("获取系统换行符:["+java.security.AccessController.doPrivileged(
                    new sun.security.action.GetPropertyAction("line.separator"))+"]");
            System.out.println("获取系统换行符:"+ AccessController.doPrivileged(new GetPropertyAction("line.separator")));
            System.out.println("获取系统换行符:"+System.getProperty("line.separator"));
            System.out.println("添加新的方法,添加换行");
            bufferedWriter.write('中');
            bufferedWriter.newLine();
            bufferedWriter.write("中a英b文c字符!");
            bufferedWriter.newLine();
            bufferedWriter.write("中a英b文c字符数组!".toCharArray());
            bufferedWriter.newLine();
            bufferedWriter.flush();//手动刷新,close会刷新
        } catch (IOException e) {
            e.printStackTrace();
        }



        try (
                FileReader fileReader = new FileReader(bufferFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            System.out.println("BufferedReader 并没有继承InputStream 是否支持mark:"+bufferedReader.markSupported());
            System.out.println("reader是否准备好:"+bufferedReader.ready());
            System.out.println("mark(1),参数生效,最小为1");
            bufferedReader.mark(1);
            System.out.println("常规读取!");
            char[] chars = new char[10];
            int length = bufferedReader.read(chars);
            System.out.println(new String(chars)+" 行数:"+length);
            bufferedReader.reset();
            System.out.println("新功能读取一行,readLine");
            while (true){
                String line = bufferedReader.readLine();
                if (line == null || line.length() == 0){
                    break;
                }
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("------------CharArrayWriter/CharArrayReader  --------------");
        String charArrayFile = "charArrayWriter";
        try (
                FileWriter fileWriter = new FileWriter(charArrayFile);
                CharArrayWriter charArrayWriter = new CharArrayWriter();

        ) {
            System.out.println("默认大小为32");
            charArrayWriter.write("中文字符串aaa");
            charArrayWriter.write("cccc".toCharArray());
            charArrayWriter.append('b').append("追加");
            charArrayWriter.writeTo(fileWriter);
            System.out.println("目前在缓存中:"+new String(charArrayWriter.toCharArray())+" "+charArrayWriter.size());
            System.out.println("目前在缓存中:"+charArrayWriter.toString());
            System.out.println("flush 不生效,因为没有地方可以输出");
            charArrayWriter.flush();
            System.out.println("reset 生效,数组大小变为0");
            charArrayWriter.reset();
            System.out.println("目前在缓存中:"+new String(charArrayWriter.toCharArray())+" "+charArrayWriter.size());
            System.out.println("目前在缓存中:"+charArrayWriter.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try(
                CharArrayReader charArrayReader = new CharArrayReader("中应为回答ssddf222".toCharArray())
        ){
            System.out.println("是否支持mark:"+charArrayReader.markSupported());
            System.out.println("流是否准备好?"+charArrayReader.ready());
            System.out.println("mark参数无效");
            charArrayReader.mark(0);
            char[] chars = new char[1024];
            int length = charArrayReader.read(chars);
            System.out.println("读取内容:"+new String(chars)+" "+length);
            charArrayReader.reset();
            chars = new char[1024];
            length = charArrayReader.read(chars);
            System.out.println("重复读取读取内容:"+new String(chars)+" "+length);

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("-----------PushbackReader  --------------");
        try (PushbackReader pushbackReader = new PushbackReader(new CharArrayReader("呵呵,(ab)1111".toCharArray()))
        ) {
            System.out.println("支持mark/reset?"+pushbackReader.markSupported());
            try {
                pushbackReader.mark(0);
                pushbackReader.reset();
            }catch (IOException e){
                System.out.println("强制使用会报异常!");
            }
            char  chars[] = new char[1];
            while (true){
                int c = pushbackReader.read(chars);
                if (c == -1){
                    break;
                }
                char tmp = chars[0];
                if (tmp == 'a'){
                    System.out.println("推回必须是本次读取的内容,不能修改后推回!");
                    pushbackReader.unread(chars);
                    char cs [] = new char[2];
                    pushbackReader.read(cs);
                    System.out.println("读取一对:"+new String(cs));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("-------------PipedReader/PipedWriter----------------");
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();

    }
}
