package com.wen233.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author: huu
 * @date: 2020/3/18 16:01
 * @description:
 */
public class RandomNumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createRandomImage(resp);
    }

    void createRandomImage(HttpServletResponse resp) throws IOException {
        resp.setHeader("refresh", "5");
//        在内存中创建一个图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
//        得到图片
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.GREEN);
//        填充背景色
        g.fillRect(0, 0, 80, 20);
        g.setColor(Color.GRAY);
//        设置字体颜色
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(makeNum(), 0, 20);
//        设置响应头控制游览器以图片方式打开
        resp.setHeader("Content-Type", "image/jpeg");
//        控制游览器不缓存
        resp.setDateHeader("expires", -1);

//        将图片写给游览器
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append("0");
        }
        return sb.toString() + num;
    }
}
