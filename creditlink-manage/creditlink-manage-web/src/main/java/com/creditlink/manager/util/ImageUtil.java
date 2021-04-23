/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

/**
 * 图片工具类
 * 
 * @version 2017年12月25日下午5:21:39
 * @author wuliu
 */
public class ImageUtil {
    
    public static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    public static BufferedImage generateCaptchaImg(String captcha, int width, int height, Font mFont, Color bgColor) {
        if (mFont == null) {
            mFont = new Font("Times New Roman", 1, 18);
        }
        if (bgColor == null) {
            bgColor = getRandColor(200, 250);
        }
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        
        g.setColor(bgColor);
        g.fillRect(0, 0, width, height);
        g.setFont(mFont);
        
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        
        g.setColor(getRandColor(160, 200));
        
        Random random = new Random();
        for (int i = 0; i < 155; i++) {
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            int x3 = random.nextInt(12);
            int y3 = random.nextInt(12);
            g.drawLine(x2, y2, x2 + x3, y2 + y3);
        }
        
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        g.drawString(captcha, 2, 16);
        
        g.dispose();
        return image;
    }
    
    public static String generateImgBase64(String imgPath) throws Exception {
        String content = "";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imgPath);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            content = new BASE64Encoder().encode(bytes);
        }
        finally {
            fis.close();
        }
        return content;
    }
    
    public static int getImgWidth(String imgPath) {
        int iRes = 0;
        File file = new File(imgPath);
        try {
            BufferedImage bi = ImageIO.read(file);
            int iH = bi.getHeight();
            iRes = bi.getWidth();
        }
        catch (Exception e) {
        }
        return iRes;
    }
    
    public static int getImgHeight(String imgPath) {
        int iRes = 0;
        File file = new File(imgPath);
        try {
            BufferedImage bi = ImageIO.read(file);
            iRes = bi.getHeight();
        }
        catch (Exception e) {
        }
        return iRes;
    }
}
