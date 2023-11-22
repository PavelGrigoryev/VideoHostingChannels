package ru.clevertec.videohostingchannels.dto.channel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelDetailedInformationResponse(Long id,
                                                 String name,
                                                 String shortDescription,
                                                 UserResponse author,
                                                 List<UserResponse> subscribers,

                                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                                 LocalDateTime createdAt,

                                                 String mainLanguage,
                                                 String avatar,
                                                 String category) {
}
