package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valerio.BingeBookBE.dto.UserLoginDTO;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;
import valerio.BingeBookBE.security.JWTTools;

@Service
public class AuthService {

    private final UserDAO userDAO;
    private final JWTTools jwtTools;

    @Autowired
    public AuthService(UserDAO userDAO, JWTTools jwtTools) {
        this.userDAO = userDAO;
        this.jwtTools = jwtTools;
    }

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {

        User user = userDAO.findByEmail(payload.email()).orElse(null);

        if (user != null && user.getPassword().equals(payload.password())) {

            return jwtTools.createToken(user);

        } else {

            return null;

        }

    }

}