package ru.clevertec.videohostingchannels.service;

import ru.clevertec.videohostingchannels.dto.user.UserRequest;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;

public interface UserService {

    UserResponse save(UserRequest request);

    UserResponse updateById(Long id, UserRequest request);

}
