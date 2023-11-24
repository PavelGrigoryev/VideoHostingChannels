package ru.clevertec.videohostingchannels.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(@NotBlank
                          @Size(max = 50)
                          String nickname,

                          @NotBlank
                          @Size(max = 50)
                          String name,

                          @Email
                          @NotBlank
                          @Size(max = 100)
                          String email) {
}
