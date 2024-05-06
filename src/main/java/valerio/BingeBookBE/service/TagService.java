package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.TagDTO;
import valerio.BingeBookBE.entity.Tag;
import valerio.BingeBookBE.repositories.TagDAO;

import java.math.BigInteger;

@Service
public class TagService {
    private final TagDAO tagDAO;

    @Autowired
    TagService(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    ///CREATE
    public Tag createTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.name());
        return tagDAO.save(tag);
    }


    ///READ
    public Page<Tag> getListTags(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.tagDAO.findAll(pageable);
    }

    ///UPDATE

    public Tag updateTag(BigInteger id, TagDTO tagDTO) {
        Tag tag = tagDAO.findById(id).orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundRole + ": " + id));
        tag.setName(tagDTO.name());
        return tagDAO.save(tag);
    }

    ///DELETE

    public void deleteTag(BigInteger id) {
        tagDAO.deleteById(id);
    }

}
