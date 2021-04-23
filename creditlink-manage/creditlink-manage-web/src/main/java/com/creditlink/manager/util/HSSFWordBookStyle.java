/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.util;

/**
 * 导出Excel格式
 * @version 2016年7月6日上午9:53:14
 * @author wuliu
 */
public class HSSFWordBookStyle {
    /**
     * 字体，默认为 宋体
     */
    private String fontName = "宋体";
    
    /**
     * 字体大小，默认为 11
     */
    private short FontHeightInPoints = 11;
    
    /**
     * 是否左右居中，1:左对齐， 2：左右居中， 3：右对齐,默认为2
     */
    private short alignment = 2;
    
    /**
     * 是否上下居中，默认为 1：上下居中
     */
    private short verticalAlignment = 1;

    public HSSFWordBookStyle() {
        
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public short getFontHeightInPoints() {
        return FontHeightInPoints;
    }

    public void setFontHeightInPoints(short fontHeightInPoints) {
        FontHeightInPoints = fontHeightInPoints;
    }

    public short getAlignment() {
        return alignment;
    }

    public void setAlignment(short alignment) {
        this.alignment = alignment;
    }

    public short getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(short verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }
}
