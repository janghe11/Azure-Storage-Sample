package net.thjang.blog.dao;

import net.thjang.blog.dto.Image;

import java.util.List;

public interface ImageDao {
    public List<Image> getImageList(Long boardId);

    public Image getImage(Long id);

    public Long uploadImage(Image image);

    public Long deleteImage(Long id);
}
