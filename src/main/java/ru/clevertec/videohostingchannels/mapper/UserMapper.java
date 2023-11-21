package ru.clevertec.videohostingchannels.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.videohostingchannels.dto.user.UserRequest;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;
import ru.clevertec.videohostingchannels.model.User;

@Mapper
public interface UserMapper {

    User fromRequest(UserRequest request);

    UserResponse toResponse(User user);

}
