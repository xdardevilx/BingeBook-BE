package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.UserLoginDTO;
import valerio.BingeBookBE.dto.UserLoginResponseDTO;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;
import valerio.BingeBookBE.security.JWTTools;

@Service
public class AuthService {

    private final UserDAO userDAO;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;

    @Autowired
    public AuthService(UserDAO userDAO, JWTTools jwtTools, PasswordEncoder bcrypt) {
        this.userDAO = userDAO;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }

    public UserLoginResponseDTO authenticateUserAndGenerateToken(UserLoginDTO payload) {

        User user = new User();
        Boolean isEquals = false;

        System.out.println(userDAO.findByEmail(payload.email()).isPresent());

        if (userDAO.findByEmail(payload.email()).isPresent()) {

            user = userDAO.findByEmail(payload.email()).get();
            isEquals = bcrypt.matches(payload.password(), user.getPassword());

            if (isEquals) {
                return new UserLoginResponseDTO(true, user, jwtTools.createToken(user));
            } else {
                throw new RuntimeException(StringConfig.errorEmailAndPassword);
            }

        } else {
            throw new RuntimeException(StringConfig.errorEmailAndPassword);
        }
    }

}