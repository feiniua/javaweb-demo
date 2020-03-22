package cn.wen233.mail.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: huu
 * @date: 2020/3/22 14:03
 * @description:
 */
public class Base64 {

    public static void main(String[] args) throws IOException {
        System.out.print("请输入用户名:");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String userName = in.readLine();
        System.out.print("请输入密码:");
        String password = in.readLine();
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println("编码后的用户名为:" + encoder.encode(userName.getBytes()));
        System.out.println("编码后的密码为:" + encoder.encode(password.getBytes()));

//        BASE64Decoder decoder = new BASE64Decoder();
//        //邮件主题的Base64编码
//        String emailSubject = "=?GBK?B?08q8/rLiytQ=?=";
//        //邮件文本内容的Base64编码
//        String emailPlainContent = "vPK1pbXE08q8/reiy82y4srUo6E=";
//        //带html标签和邮件内容的Base64编码
//        String emailHtmlContent = "PFA+vPK1pbXE08q8/reiy82y4srUo6E8L1A+";
//        //将使用Base64编码过后的文本内容再使用Base64来解码
//        emailSubject = new String(decoder.decodeBuffer(emailSubject), "GBK");
//        emailPlainContent = new String(decoder.decodeBuffer(emailPlainContent), "GBK");
//        emailHtmlContent = new String(decoder.decodeBuffer(emailHtmlContent), "GBK");
//        System.out.println("邮件标题：" + emailSubject);
//        System.out.println("邮件内容：" + emailPlainContent);
//        System.out.println("带html标签的邮件内容：" + emailHtmlContent);
    }
}
