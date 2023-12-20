package ru.clevertec.videohostingchannels.dto.user;

public record UserResponse(Long id,
                           String nickname,
                           String name,
                           String email) {
}
