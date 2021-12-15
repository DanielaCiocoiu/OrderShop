package server.convert;

import lib.dto.UserDTO;
import lib.dto.UserIdDTO;
import server.model.Order;
import server.model.Product;
import server.model.User;
import server.model.UserId;

public class UserConvertor {

    private UserConvertor() {}

    public static User convert(UserDTO userDTO) {
        UserId userId = new UserId();

                userId.setUserName(userDTO.getUserId().getUserName());

        User user = new User();
                user.setUserId(userId);
                user.setPassword(userDTO.getPassword());
        return user;
    }

    public static UserDTO convert(User user) {
        UserIdDTO userIdDto = new UserIdDTO();
                    userIdDto.setUserName(user.getUserId());

        UserDTO userDto = new UserDTO();
                    userDto.setUserId(userIdDto);
                    userDto.setPassword(user.getPassword());
       return userDto;
    }

}
