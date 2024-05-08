package valerio.BingeBookBE.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
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
import valerio.BingeBookBE.dto.UserDTO;
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
    UserService(Cloudinary cloudinary, UserDAO userDAO, PasswordEncoder bcryptEncoder,
            PersonalDataService personalDataService, RoleService roleService) {
        this.cloudinary = cloudinary;
        this.userDAO = userDAO;
        this.bcryptEncoder = bcryptEncoder;
        this.personalDataService = personalDataService;
        this.roleService = roleService;
    }

    /// CREATE
    public User saveUser(UserDTO userDto, PersonalDataDTO personalDataDTO) throws IOException {
        User user = new User();
        String url = (String) cloudinary.uploader().upload(userDto.profilePicture().getBytes(), ObjectUtils.emptyMap())
                .get("url");

        user.setProfilePicture(url);
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));

        /// Create PersonalData
        user.setPersonalDataId(personalDataService.createPersonalData(personalDataDTO));

        /// Set Role
        user.setRole(roleService.getRoleByName(RoleEnum.USER.toString()));

        return userDAO.save(user);
    }

    /// READ
    public User getUserById(BigInteger id) {
        return userDAO.findById(id).orElse(null);
    }

    public Page<User> getUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userDAO.findAll(pageable);
    }

    /// UPDATE
    public User updateUser(BigInteger id, UserDTO userDto) throws IOException {
        User userToUpdate = userDAO.findById(id).orElse(null);
        if (userToUpdate == null) {
            return null;
        }

        String url = (String) cloudinary.uploader().upload(userDto.profilePicture().getBytes(), ObjectUtils.emptyMap())
                .get("url");

        userToUpdate.setProfilePicture(url);
        userToUpdate.setUsername(userDto.username());
        userToUpdate.setEmail(userDto.email());
        userToUpdate.setPassword(bcryptEncoder.encode(userDto.password()));
        return userDAO.save(userToUpdate);
    }

    /// DELETE
    public void deleteUser(BigInteger id) {
        userDAO.deleteById(id);
    }
}
