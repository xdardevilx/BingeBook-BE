package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.TagDTO;
import valerio.BingeBookBE.entity.Tag;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;
import valerio.BingeBookBE.service.TagService;

import java.math.BigInteger;


@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final UserDAO userDAO;

    @Autowired
    public TagController(TagService tagService, UserDAO userDAO) {
        this.tagService = tagService;
        this.userDAO = userDAO;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTag(TagDTO tagDTO) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userDAO.findByUsername(userDetails.getUsername()).orElse(null);

        Tag tag = tagService.createTag(tagDTO, user.getId());
        return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public Page<Tag> getListTags(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return tagService.getListTags(page, size, sortBy);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTag(BigInteger id, TagDTO tagDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userDAO.findByUsername(userDetails.getUsername()).orElse(null);

        Tag tag = tagService.updateTag(id, tagDTO, user.getId());
        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteTag(BigInteger id) {
        tagService.deleteTag(id);
    }

}
