package valerio.BingeBookBE.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import valerio.BingeBookBE.dto.TagDTO;
import valerio.BingeBookBE.entity.Tag;
import valerio.BingeBookBE.service.TagServiceImpl;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagServiceImpl tagService;

    @Autowired
    public TagController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTag(@RequestBody @Validated TagDTO tagDTO, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        tagService.createTagOfUser(tagDTO, userId);

        return ResponseEntityCustom.responseSuccess("Tag created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTags(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        List<Tag> tags = tagService.getAllTagsOfUser(userId);

        return ResponseEntityCustom.responseSuccess(tags, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Long id, @Validated @RequestBody TagDTO tagDTO) {

        tagService.updateTag(id, tagDTO);

        return ResponseEntityCustom.responseSuccess("Tag updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteTag(Long id) {
        tagService.deleteTag(id);
    }

}
