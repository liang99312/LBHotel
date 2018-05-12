/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lb.lbtravel.util;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 *
 * @author Jane
 */
public class PinYinUtil {
    
    public static String ctoE(String s) {
        if (s == null) {
            return "";
        }
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (String.valueOf(s.charAt(i)).equals(" ")) {
                result = result + " ";
                continue;
            }
            String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(s.charAt(i));
            if (pinyins == null) {
                result = result + s.charAt(i);
            } else {
                result = result + pinyins[0].charAt(0);
            }
        }
        result = result.toUpperCase();
        return result;
    }
    //比如：李小明--lixm
    public static String ctoE_a0190(String s) {
        if (s == null) {
            return "";
        }
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (String.valueOf(s.charAt(i)).equals(" ")) {
                result = result + " ";
                continue;
            }
            String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(s.charAt(i));
            if (i == 0) {
                result = result + pinyins[0].substring(0, pinyins[0].length()-1);
            } else {
                if (pinyins == null) {
                    result = result + s.charAt(i);
                } else {
                    result = result + pinyins[0].charAt(0);
                }
            }
        }
        result = result.toLowerCase();
        return result;
    }
    
    public static void main(String args[]){
        System.out.println(ctoE("梁大明"));
    }
}
