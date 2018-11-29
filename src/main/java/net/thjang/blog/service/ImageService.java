package net.thjang.blog.service;

import net.thjang.blog.dto.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface ImageService {

    public List<Image> getImageList(Long boardId);

    public Image getImage(Long board_id);

    public Long uploadImage(Image image);

    public int deleteImage(Long id);

    public void uploadImageToAzure(MultipartFile file, File sourceFile, String datePath, String path);





}
