package com.peter.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Author:peter
 * @Date:2020/11/8 16:05
 */
@RestController
@RequestMapping("/video/")
public class VideoController {

    /**
     * mpsRegionId：
     * 这是服务器所在的地理位置
     * 目前我个人账号的媒体服务器所在地是华东1（杭州）
     * 所以mpsRegionId 等于 cn.hangzhou
     * */

    private static String accessKeyId = "LTAI4G2fzNeUAmTL9T3isEQd";
    private static String accessKeySecret = "YCmLwT8UYmdFREqfnllMQylByZMSQy";
    private static String regionIds = "cn-hangzhou";

    public void upload(MultipartFile uploadFile){
        if (uploadFile == null){
            new Exception("空文件，请重新上传");
        }


    }


    /**
     * 上传文件
     * @param file
     * @throws IOException
     */
    @RequestMapping("uploadFile")
    @ResponseBody
    public String saveFile(MultipartFile file) throws IOException {

        if (file == null){
            return "上传的文件不合法，请重新上传";
        }

        InputStream inputStream = file.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);

        String outPath = "/Users/peter/Documents/temp/";
        File outFile = new File(outPath + "out.mp4");
        BufferedOutputStream bos = null;

        byte[] buff = new byte[1024];
        int len = 0;

        try{
            bos = new BufferedOutputStream(new FileOutputStream(outFile));

            while ((len = bis.read(buff)) != -1){
                bos.write(buff,0,len);
            }
            return "save successful ...";
        }catch(Exception e){
            e.printStackTrace();
            return "save fail";
        }finally {
            bis.close();
            bos.close();
            System.out.println("close ... ");
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/peter/Documents/temp/sea.mp4");
        InputStream inputStream = new FileInputStream(file);

        /*BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buff = new byte[1024];

        File outFile = new File("/Users/peter/Documents/temp/outsea.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

        int len = 0;

        while((len = bis.read(buff)) != -1){
            bos.write(buff,0,len);
        }

        bis.close();
        bos.close();*/


        //saveFile(inputStream,"out.mp4");

        System.out.println("successful...");

    }


}
