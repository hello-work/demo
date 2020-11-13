package com.peter.demo.controller;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

/**
 * @Author:peter
 * @Date:2020/11/9 01:10
 */
public class UploadVideoDemo {

    private static final String ACCESSID = "LTAI4G2fzNeUAmTL9T3isEQd";
    private static final String ACCESSSECRET = "YCmLwT8UYmdFREqfnllMQylByZMSQy";
    private static String regionId = "cn-shanghai";  // 点播服务接入区域

    public static void main(String[] args) {

        /*测试视频上传
        String title = "ball.mp4";
        String fileName = "/Users/peter/Documents/temp/ball.mp4";

        testUploadVideo(ACCESSID,ACCESSSECRET,title,fileName);*/

        /*测试获取视频播放地址*/
        try{
            System.out.println("开始获取视频播放地址");
            GetPlayInfoResponse playInfo = getPlayInfo(initClient());
            List<GetPlayInfoResponse.PlayInfo> playInfos = playInfo.getPlayInfoList();
            for (GetPlayInfoResponse.PlayInfo info : playInfos){
                if (info.getFormat().equals("mp4") && info.getDefinition().equals("OD")){
                    System.out.println("播放地址 ： " + info.getPlayURL());
                    System.out.println("清晰度 ： " + info.getDefinition());
                }

            }
            System.out.println("视频标题 ： " + playInfo.getVideoBase().getTitle());

            System.out.println("获取成功");
        } catch (Exception e){
            System.out.println("获取失败");
            e.printStackTrace();
        }
        
    }

    /**
     * 初始化客户端
     * @return
     */
    public static DefaultAcsClient initClient (){
        DefaultProfile profile = DefaultProfile.getProfile(regionId, ACCESSID, ACCESSSECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /**
     * 上传视频方法
     * @param accessId
     * @param accessSecret
     * @param title
     * @param fileName
     */
    public static void testUploadVideo(String accessId,String accessSecret,String title,String fileName){
        UploadVideoRequest request = new UploadVideoRequest(accessId, accessSecret, title, fileName);
        request.setPartSize(1 * 1024 * 1024L);
        //request.setEcsRegionId("cn-shanghai");

        UploadVideoImpl upload = new UploadVideoImpl();
        UploadVideoResponse response = upload.uploadVideo(request);

        /**请求视频点播服务的请求ID*/
        System.out.print("RequestId=" + response.getRequestId() + "\n");
        if (response.isSuccess()){
            System.out.println("success");
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        }else {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 获取视频播放地址
     * @param client
     * @return
     * @throws Exception
     */
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception{

        String videoId = "7cb382272b8f4d67b14c16d28a6f3fb6";

        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoId);

        return client.getAcsResponse(request);
    }




}
