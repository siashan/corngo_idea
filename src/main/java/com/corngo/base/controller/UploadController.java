package com.corngo.base.controller;

import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.spring.Configs;
import jodd.io.FileNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 文件上传
 *
 * Created by kfc on 2016/8/9.
 */
@Controller
@RequestMapping("fs/service")
public class UploadController extends BaseController {
    private Logger log = LoggerFactory.getLogger(UploadController.class);

    @ResponseBody
    @RequestMapping(value = "upload" )
    public Object upload(MultipartFile upfile,HttpServletRequest request){
        Calendar c = Calendar.getInstance();
        String filedir=request.getSession().getServletContext().getRealPath("/upload");
        filedir = getFilePath(c,filedir);
        System.out.println(filedir);
        File dir = new File(filedir);
        if (!dir.exists()){
            dir.mkdirs();
        }
        Random random = new Random();
        String prefix = getFileName(c) + ""+(random.nextInt(9999-1000+1)+1000);
        log.info("receive upload file : {}" , upfile);
        log.info("file name :{}",upfile.getName());
        log.info("file content type :{}",upfile.getContentType());
        log.info("file OriginalFilename : {}",upfile.getOriginalFilename());
        log.info("file size :{}",upfile.getSize());
        File targent = new File(filedir+File.separator+prefix+"."+ FileNameUtil.getExtension(upfile.getOriginalFilename()));
        String status = "SUCCESS";
        try {
            upfile.transferTo(targent);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",upfile.getName());
        map.put("originalName",upfile.getOriginalFilename());
        map.put("size",upfile.getSize());
        map.put("state",status);
        map.put("type", FileNameUtil.getExtension(upfile.getOriginalFilename()));
        map.put("url",filedir.split("upload")[1]+File.separator+prefix+"."+ FileNameUtil.getExtension(upfile.getOriginalFilename()));
        return map;
    }


    /**
     * 图片展示
     *
     * @param imgUrl
     */
    @ResponseBody
    @RequestMapping("showImg")
    public String  showImg(String imgUrl,HttpServletResponse response) throws IOException {
        String filedir = Configs.getProperty("file.upload.path");
        String fileName = filedir + imgUrl;
        File file = new File(fileName);
       OutputStream outputStream = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        response.setContentType("image/*");
        response.reset();
        int count = 0;
        byte[] buffer = new byte[1024 * 8];
        while ((count = in.read(buffer)) != -1) {
            outputStream.write(buffer, 0, count);
            outputStream.flush();
        }
        outputStream.close();
        in.close();
        return "ok";
    }





    /**
     * 获取文件路径
     * @param c
     * @return
     */
    private String getFilePath(Calendar c, String filedir){
        //路径规则 D:/eletaxi/upload/2017/05/05/201705051206235598.png
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        filedir = filedir  + year + File.separator + (month<=9?"0"+month:month) + File.separator + ((day<=9?"0"+day:day));
        return filedir;
    }

    /**
     * 获取文件名称 201705051555555555
     * @param c
     * @return
     */
    private String getFileName(Calendar c){
        String fileName = "";
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        fileName = year + "" + (month<=9?"0"+month:month) + "" + ((day<=9?"0"+day:day)) + "" + ((hour<=9?"0"+hour:hour)) + "" + ((minute<=9?"0"+minute:minute)) + "" + ((second<=9?"0"+second:second));
        return fileName;
    }
}
