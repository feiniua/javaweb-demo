package com.wen233.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author: huu
 * @date: 2020/3/18 15:28
 * @description:
 */
public class DownServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        downImage(resp);
    }

    void downImage(HttpServletResponse resp) throws IOException {
        System.out.println("down---------------------");
//        获取文件绝对路径
        String realPath = "E:\\IdeaProjects\\demo-web\\web-1\\src\\main\\resources\\image.png";
        String path = this.getServletContext().getRealPath("\\image.png");
        System.out.println(path);
//        获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
//        设置content-disposition响应头控制浏览器以下载的形式打开文件
//        中文名要编码
//        response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
        resp.setHeader("content-disposition", "attachment;filename="+fileName);
//        获得需要下载的文件输入流
        InputStream in = new FileInputStream(realPath);
        int len = 0;
//        创建缓冲区
        byte[] buffer = new byte[1024];
        OutputStream out = resp.getOutputStream();
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }
}
