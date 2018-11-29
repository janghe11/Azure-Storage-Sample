package net.thjang.blog.controller;

import net.thjang.blog.dao.ImageDao;
import net.thjang.blog.dto.Image;
import net.thjang.blog.service.ImageService;
import net.thjang.blog.util.AzureApp;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Controller
@Log
public class UploadController {

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value="/uploadForm" , method= RequestMethod.GET)
    public void uploadForm() {

    }
    @RequestMapping(value="/uploadForm" , method = RequestMethod.POST)
    public void uploadForm(MultipartFile[] files, Model model) {
        for(MultipartFile file : files) {
            File dir = new File(".");
            String path = dir.getAbsolutePath();

            String datePath = AzureApp.calcPath(path);

            // /tmp/2018_11_01
            path = path + datePath;

            UUID uid = UUID.randomUUID();
            String savedName = uid.toString() + "_" + file.getOriginalFilename();

            File sourceFile = new File(path , savedName);


            Image image = new Image();

            image.setOriginalName(file.getOriginalFilename());
            image.setType(file.getContentType());
            image.setSize(file.getSize());
            image.setSaveName(savedName);
            image.setPath(datePath + File.separator +savedName);
            image.setRegDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            image.setBoardId(3L);// 임시번호. 원래는 파라미터로 받아야함.

            imageService.uploadImage(image);
            // 이미지 db업로드 완료.

            imageService.uploadImageToAzure(file,sourceFile,datePath,path);

        }


    }


}
