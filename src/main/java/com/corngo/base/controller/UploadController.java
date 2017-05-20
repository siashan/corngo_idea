package com.corngo.base.controller;

import com.corngo.base.support.controller.BaseController;
import jodd.io.FileNameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
    public Object upload(/*@RequestParam(value = "file" ,required = false) */MultipartFile upfile,HttpServletRequest request){
        String filedir=request.getSession().getServletContext().getRealPath("/resource/upload");
        System.out.println(filedir);
        File dir = new File(filedir);
        if (!dir.exists()){
            dir.mkdirs();
        }
        String prefix = UUID.randomUUID().toString().replaceAll("-","");
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
        map.put("url",prefix+"."+ FileNameUtil.getExtension(upfile.getOriginalFilename()));
        return map;
    }
}
