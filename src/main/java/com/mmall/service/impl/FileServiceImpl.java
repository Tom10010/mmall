package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zhang on 2018/3/19.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService{

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename(); //获得上传文件的文件名
        //获取扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." +fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名:{},上传路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true); //赋予权限
            fileDir.mkdirs();  //
        }

        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
            //文件上传成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器

            //删除upload中的文件
            targetFile.delete();
        } catch (IOException e) {
            logger.error("文件上传失败", e);
            return null;
        }
        return targetFile.getName();
    }
}
