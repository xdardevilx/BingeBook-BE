package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import valerio.BingeBookBE.dto.UserLoginDTO;
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

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
        
        User user = userDAO.findByEmail(payload.email()).orElse(null);

        Boolean isEquals = bcrypt.matches(payload.password(), user.getPassword());

        if (user != null && isEquals) {

            return jwtTools.createToken(user);

        } else {

            return null;

        }

    }

}