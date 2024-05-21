package valerio.BingeBookBE.service;

import valerio.BingeBookBE.config.StringConfig;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import valerio.BingeBookBE.config.RoleEnum;
import valerio.BingeBookBE.dto.PersonalDataDTO;
import valerio.BingeBookBE.dto.UserDTO;
import valerio.BingeBookBE.dto.response.PaginatedResponse;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;
import valerio.BingeBookBE.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final CloudinaryService cloudinaryService;
    private final UserDAO userDAO;
    private final PasswordEncoder bcryptEncoder;
    private final PersonalDataServiceImpl personalDataService;
    private final RoleServiceImpl roleService;

    @Autowired
    UserServiceImpl(CloudinaryService cloudinaryService, UserDAO userDAO,
            PasswordEncoder bcryptEncoder,
            @Lazy PersonalDataServiceImpl personalDataService, RoleServiceImpl roleService) {
        this.cloudinaryService = cloudinaryService;
        this.userDAO = userDAO;
        this.bcryptEncoder = bcryptEncoder;
        this.personalDataService = personalDataService;
        this.roleService = roleService;
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with ID %d not found", id)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with email %s not found", email)));
    }

    @Override
    public User getUserByEmailOrUsername(String email, String username) {
        return userDAO.findByEmailOrUsername(email, username)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with email %s or username %s not found", email, username)));
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDAO.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch all users", e);
        }
    }

    @Override
    public PaginatedResponse<User> getAllUsersWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<User> userPage = userDAO.findAll(pageable);
        return new PaginatedResponse<>(userPage.getContent(), userPage.getNumber(), userPage.getSize(),
                userPage.getTotalPages(), userPage.getTotalElements(), userPage.getSort().toString());
    }

    @Override
    public void createUser(UserDTO userDto, PersonalDataDTO personalDataDto) {
        if (userDAO.existsByEmailOrUsername(userDto.email(), userDto.username())) {
            throw new EntityNotFoundException(
                    StringConfig.errorNotFoundUser + ": " + userDto.email() + " or " + userDto.username());
        }

        User user = new User();

        user.setUsername(userDto.username().toLowerCase());
        user.setEmail(userDto.email().toLowerCase());
        user.setPassword(bcryptEncoder.encode(userDto.password()));

        if (userDto.profilePicture() != null) {
            user.setProfilePicture(cloudinaryService.uploadImageFromMultipartFile(userDto.profilePicture()));
        }

        /// Set Role
        user.setRoleRef(roleService.getRoleByName(RoleEnum.USER.toString()));

        try {
            User userSaved = userDAO.save(user);
            personalDataService.createPersonalDataOfUser(personalDataDto, userSaved.getId());
            user.setPersonalData(personalDataService.getPersonalDataById(userSaved.getId()));

            userDAO.save(user);
        } catch (Exception e) {
            throw new ServiceException("Failed to create user", e);
        }

        /// Create PersonalData
    }

    @Override
    public void updateUser(Long id, UserDTO userDto) {
        if (!userDAO.existsById(id)) {
            throw new EntityNotFoundException(StringConfig.errorNotFoundUser + ": " + id);
        }

        User user = getUserById(id);

        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(bcryptEncoder.encode(userDto.password()));

        if (userDto.profilePicture() != null) {
            user.setProfilePicture(cloudinaryService.uploadImageFromMultipartFile(userDto.profilePicture()));
        }

        try {
            userDAO.save(user);
        } catch (Exception e) {
            throw new ServiceException("Failed to update user", e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (!userDAO.existsById(id)) {
            throw new EntityNotFoundException(StringConfig.errorNotFoundUser + ": " + id);
        }
        userDAO.deleteById(id);
        personalDataService.deletePersonalDataById(id);
    }

    // @Override
    // public boolean existUserByEmail(String email) {
    // if (userDAO.existsByEmail(email)) {
    // return true;
    // } else {
    // throw new EntityNotFoundException(StringConfig.errorNotFoundUser + ": " +
    // email);
    // }
    // }
    // private boolean existUserById(Long id) {
    // if (userDAO.existsById(id)) {
    // return true;
    // } else {
    // throw new EntityNotFoundException(StringConfig.errorNotFoundUser + ": " +
    // id);
    // }
    // }

    // private boolean existUserByEmailOrUsername(String email, String username) {
    // if (userDAO.existsByEmailOrUsername(email, username)) {
    // return true;
    // } else {
    // throw new EntityNotFoundException(StringConfig.errorNotFoundUser + ": " +
    // email + " or " + username);
    // }
    // }
}
