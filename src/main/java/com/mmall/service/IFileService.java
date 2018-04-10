package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhang on 2018/3/19.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
