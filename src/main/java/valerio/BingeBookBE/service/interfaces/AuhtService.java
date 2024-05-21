package valerio.BingeBookBE.service.interfaces;

import valerio.BingeBookBE.dto.UserLoginDTO;

public interface AuhtService {
    String authenticateUserAndGenerateToken(UserLoginDTO payload);
}
