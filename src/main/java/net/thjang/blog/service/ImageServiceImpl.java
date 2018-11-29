package net.thjang.blog.service;

import net.thjang.blog.dao.ImageDao;
import net.thjang.blog.dto.Image;
import net.thjang.blog.util.AzureApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public List<Image> getImageList(Long boardId) {

        return imageDao.getImageList(boardId);
    }

    @Override
    public Image getImage(Long id) {
        return imageDao.getImage(id);
    }

    @Override
    public Long uploadImage(Image image) {
        Long id =imageDao.uploadImage(image);
        return id;
    }

    @Override
    public int deleteImage(Long id) {
        return 0;
    }


    @Override
    public void uploadImageToAzure(MultipartFile file, File sourceFile,String datePath,String path) {
        AzureApp app = new AzureApp();
        try {
            FileCopyUtils.copy(file.getBytes(),sourceFile);
            String url = datePath.substring(1) + File.separator +sourceFile.getName();

            app.execAzure(url,sourceFile.getAbsolutePath());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            sourceFile.delete();
            path = path + File.separator;
            File deleteDir = new File(path);
            deleteDir.delete();

        }

    }
}
