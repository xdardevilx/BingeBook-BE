package valerio.BingeBookBE.service.interfaces;

import java.util.List;

import valerio.BingeBookBE.dto.PersonalDataDTO;
import valerio.BingeBookBE.dto.UserDTO;
import valerio.BingeBookBE.dto.response.PaginatedResponse;
import valerio.BingeBookBE.entity.User;

public interface UserService {
    User getUserById(Long id);

    User getUserByEmail(String email);

    User getUserByEmailOrUsername(String email, String username);

    List<User> getAllUsers();

    PaginatedResponse<User> getAllUsersWithPagination(int page, int size, String sortBy);

    void createUser(UserDTO userDto, PersonalDataDTO personalDataDto);

    void updateUser(Long id, UserDTO userDto);

    void deleteUser(Long id);
}
