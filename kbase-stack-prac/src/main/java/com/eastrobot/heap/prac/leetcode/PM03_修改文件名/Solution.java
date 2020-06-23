package com.eastrobot.heap.prac.leetcode.PM03_修改文件名;


import java.io.File;
import java.text.DecimalFormat;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/17 16:04
 */
public class Solution {

    public static void main(String[] args) {

        DecimalFormat decimalFormat = new DecimalFormat("000");

        File dir = new File("C:\\Users\\zhanzhao\\Desktop\\0621活动");
        if (dir.isDirectory()){
            System.out.println(dir.getAbsolutePath());
            System.out.println(dir.getPath());
            File[] files = dir.listFiles();
            int i=1;
            for (File file : files){
                System.out.println(file.getAbsolutePath());
                if (file.getName().contains("微信图片")){
                    file.renameTo(new File(dir.getAbsolutePath() + "/" + decimalFormat.format(i) + file.getName().substring(file.getName().indexOf("."))));
                }
                i++;
            }
        }


    }
}
