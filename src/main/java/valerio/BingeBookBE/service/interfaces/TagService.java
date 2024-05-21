package valerio.BingeBookBE.service.interfaces;

import java.util.List;

import valerio.BingeBookBE.dto.TagDTO;
import valerio.BingeBookBE.entity.Tag;

public interface TagService {
    Tag getTagById(Long id);

    Tag getTagByNameOfUser(String name, Long userId);

    List<Tag> getAllTagsOfUser(Long userId);

    void createTagOfUser(TagDTO Tag, Long userId);

    void updateTag(Long id, TagDTO Tag);
    
    void deleteTag(Long id);
}
