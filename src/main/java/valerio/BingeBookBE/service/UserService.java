package valerio.BingeBookBE.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;

public class UserService {
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder bcryptEncoder;


    ///CREATE

    ///READ
    public Page<User> getUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userDAO.findAll(pageable);
    }

    ///UPDATE


    ///DELETE


}
