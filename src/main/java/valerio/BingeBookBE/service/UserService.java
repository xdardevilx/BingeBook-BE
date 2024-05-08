package valerio.BingeBookBE.service;

import com.cloudinary.Cloudinary;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import valerio.BingeBookBE.config.RoleEnum;
import valerio.BingeBookBE.dto.PersonalDataDTO;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;

@Service
public class UserService {

    private final Cloudinary cloudinary;
    private final UserDAO userDAO;
    private final PasswordEncoder bcryptEncoder;
    private final PersonalDataService personalDataService;
    private final RoleService roleService;

    @Autowired
    UserService(Cloudinary cloudinary, UserDAO userDAO, PasswordEncoder bcryptEncoder, PersonalDataService personalDataService, RoleService roleService) {
        this.cloudinary = cloudinary;
        this.userDAO = userDAO;
        this.bcryptEncoder = bcryptEncoder;
        this.personalDataService = personalDataService;
        this.roleService = roleService;
    }

    ///CREATE
    public User saveUser(User user, PersonalDataDTO personalDataDTO) {
        user.setProfilePicture(cloudinary.url().generate(user.getProfilePicture()));
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));


        ///Create PersonalData
        user.setPersonalDataId(personalDataService.createPersonalData(personalDataDTO));

        ///Set Role
        user.setRole(roleService.getRoleByName(RoleEnum.USER.toString()));

        return userDAO.save(user);
    }

    ///READ
    public User getUserById(BigInteger id) {
        return userDAO.findById(id).orElse(null);
    }

    public Page<User> getUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userDAO.findAll(pageable);
    }

    ///UPDATE
    public User updateUser(BigInteger id, User user) {
        User userToUpdate = userDAO.findById(id).orElse(null);
        if (userToUpdate == null) {
            return null;
        }
        userToUpdate.setProfilePicture(cloudinary.url().generate(user.getProfilePicture()));
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDAO.save(userToUpdate);
    }


    ///DELETE
    public void deleteUser(BigInteger id) {
        userDAO.deleteById(id);
    }
}
