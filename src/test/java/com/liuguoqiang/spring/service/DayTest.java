package com.liuguoqiang.spring.service;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author liuguoqiang
 * @version v 0.1 2018/10/8
 * @Description 使用java代码下载到此网络文件：https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DayTest {
    @Test
    /**
     * 使用传统io stream 下载文件
     *
     */
    public void downloadTest() {
        InputStream ins = null;
        BufferedOutputStream bos = null;
        try {
            // System.setProperty("https.protocols", "TLSv1");
            ins = new URL("https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js").openStream();
            File file = new File("D:\\jquery.js");
            bos = new BufferedOutputStream(new FileOutputStream(file));
            int count = 0;
            byte[] bytes = new byte[1024];
            while ((count = ins.read(bytes)) != -1) {
                bos.write(bytes, 0, count);
            }
            System.out.println("文件下载成功!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ins.close();
                bos.close();
            } catch (IOException e) {
                System.out.println("流关闭失败：" + e.getMessage());
            }
        }
    }


    @Test
    /**
     * 利用 commonio 库下载文件，依赖Apache Common IO ，官网 https://commons.apache.org/proper/commons-io/
     *
     */
    public void downloadByApacheCommonIO() {
        try {
            FileUtils.copyURLToFile(new URL("https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"), new File("D:\\jquery.js"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * 使用NIO下载文件， 需要 jdk 1.7+
     * @param url
     * @param saveDir
     * @param fileName
     */
    public void downloadByNIO2() {
        try (InputStream ins = new URL("https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js").openStream()) {
            Path target = Paths.get("D:\\jquery.js");
            Files.createDirectories(target.getParent());
            Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("文件下载成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    /**
     * 读取C:盘下user.json文件，如不存在则创建并写入{name:xxx,location:xxx}，拷贝至D盘并重命名为user_copy.json
     * @param url
     * @param saveDir
     * @param fileName
     */
    public void fileOpt() throws IOException {
        String fileName = "D:\\user.json";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        String content = "{name:'保密',location:'杭州'}";
        FileOutputStream os = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        bos.write(content.getBytes());
        bos.flush();
        os.close();
        bos.close();

        FileInputStream input = new FileInputStream(file);
        FileOutputStream output = new FileOutputStream(new File("D:\\user_copy.json"));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = input.read(bytes)) > 0) {
            output.write(bytes, 0, len);
        }
        output.flush();
        input.close();
        output.close();
    }

    @Test
    /**
     * 在E盘下放置两个mp3音频文件，使用流操作将两个音频文件合并到一个mp3文件中，实现歌曲串烧。
     * @param url
     * @param saveDir
     * @param fileName
     */
    public void MusicCompound() throws IOException {
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        String fileNames[] = {"E:/CloudMusic/悟空.mp3", "E:/CloudMusic/没你的周末.mp3"};
        //设置byte数组，每次往输出流中传入8K的内容
        byte by[] = new byte[1024 * 8];
        try {
            fileOutputStream = new FileOutputStream("E:/CloudMusic/合并.mp3");
            for (int i = 0; i < 2; i++) {
                int count = 0;
                fileInputStream = new FileInputStream(fileNames[i]);
                //跳过前面3M的歌曲内容
                fileInputStream.skip(1024 * 1024 * 3);
                while (fileInputStream.read(by) != -1) {
                    fileOutputStream.write(by);
                    count++;
                    System.out.println(count);
                    //要截取中间2MB的内容，每次输入8k的内容，所以输入的次数是1024*2/8
                    if (count == (1024 * 2 / 8)) {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //输出完成后关闭输入输出流
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void BufferedStreamTest() throws IOException {
        //读取文件的缓冲输入字节流
        BufferedInputStream in = null;
        //写入文件的缓冲输出字节流
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream("D:\\test\\test.jpg"));
            File file = new File("D:\\test\\test1.jpg");
            if (file != null) {
                file.createNewFile();
            }
            out = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = new byte[1024];
            //每次读取到的字节数组的长度
            int length;
            while ((length = in.read(bytes)) > 0) {
                //写入到输出流
                out.write(bytes, 0, length);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    // 关闭缓冲输入流
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    // 关闭缓冲输出流
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
