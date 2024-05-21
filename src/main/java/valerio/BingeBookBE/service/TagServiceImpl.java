package valerio.BingeBookBE.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.TagDTO;
import valerio.BingeBookBE.entity.Tag;
import valerio.BingeBookBE.repositories.TagDAO;
import valerio.BingeBookBE.service.interfaces.TagService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    private final TagDAO tagDAO;
    private final UserServiceImpl userService;

    @Autowired
    TagServiceImpl(TagDAO tagDAO, UserServiceImpl userService) {
        this.tagDAO = tagDAO;
        this.userService = userService;
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundTag + ": " + id));
    }

    @Override
    public Tag getTagByNameOfUser(String name, Long userId) {
        return tagDAO.findByNameByUserRef(name, userService.getUserById(userId))
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundTag + ": " + name));
    }

    @Override
    public List<Tag> getAllTagsOfUser(Long userId) {
        try {
            return tagDAO.findAllByUserRef(userService.getUserById(userId));
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch all tags", e);
        }
    }

    @Override
    public void createTagOfUser(TagDTO tagDto, Long userId) {
        if (tagDAO.existsByName(tagDto.name().toLowerCase())) {
            throw new EntityNotFoundException(StringConfig.errorAlreadyExistsTag + ": " + tagDto.name());
        }

        Tag tag = new Tag();
        tag.setName(tagDto.name().toLowerCase());
        tag.setUserRef(userService.getUserById(userId));

        tagDAO.save(tag);
    }

    @Override
    public void updateTag(Long id, TagDTO Tag) {
        if (tagDAO.existsById(id)) {
            throw new EntityNotFoundException(StringConfig.errorAlreadyExistsTag + ": " + id);
        }

        if (tagDAO.existsByName(Tag.name().toLowerCase())) {
            throw new EntityNotFoundException(StringConfig.errorAlreadyExistsTag + ": " + Tag.name());
        }

        Tag tag = getTagById(id);
        tag.setName(Tag.name().toLowerCase());

        tagDAO.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        if (!tagDAO.existsById(id)) {
            throw new EntityNotFoundException(StringConfig.errorNotFoundTag + ": " + id);
        }
        tagDAO.deleteById(id);
    }

    /// INTERNAL METHODS
    public interface TagsIdsProvider {
        Set<Long> tagIds();
    }

    public <T extends TagsIdsProvider> Set<Tag> hashSetTags(T t) {
        Set<Tag> tags = new HashSet<>();
        for (Long tagId : t.tagIds()) {
            Tag tag = getTagById(tagId);
            tags.add(tag);
        }
        return tags;
    }
}
