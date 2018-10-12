package com.liuguoqiang.spring.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuguoqiang
 * @version v 0.1 2018/10/8
 * @Description ShowFileTest.java
 */
public class ShowFileTest {
        private static String dirPath = "E:\\Foxmail";
        private static Map<String,List<File>> allMapFiles = new HashMap<>();

        public static void main(String[] args) {
            File file = new File(dirPath);
            listDirectoryFile(file);

            System.out.println("总目录数：" + allMapFiles.size());
            for(Map.Entry entry : allMapFiles.entrySet()){
                System.out.println("=========目录："+entry.getKey()+"=========");
                Long minFileLen = Long.MAX_VALUE;
                String minFileName = "";
                List<File> filesInDir = (List<File>) entry.getValue();
                if(filesInDir.size() > 0){
                    for(File f : (List<File>)entry.getValue()){
                        if(f.length() < minFileLen){
                            minFileLen = f.length();
                            minFileName = f.getAbsolutePath();
                        }
                    }
                    System.out.println(entry.getKey() + "目录下最小的文件是：" + minFileName + "；length = " + minFileLen);
                }else{
                    System.out.println(entry.getKey() + "目录下无文件单元");
                }
                System.out.println("----------遍历结束-----------\n");
            }
        }

        /**
         * 递归
         * @param dirFile
         */
        private static void listDirectoryFile(File dirFile){
            ArrayList<File> filesInDir = new ArrayList<>();
            allMapFiles.put(dirFile.getAbsolutePath(),filesInDir);

            File[] tempFiles = dirFile.listFiles();
            if(tempFiles == null || tempFiles.length == 0){
                return ;
            }
            for(File f : tempFiles){
                if(f.isFile()){
                    allMapFiles.get(dirFile.getAbsolutePath()).add(f);
                }else{
                    listDirectoryFile(f);
                }
            }
        }

}
