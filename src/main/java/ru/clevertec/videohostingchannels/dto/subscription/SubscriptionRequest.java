package ru.clevertec.videohostingchannels.dto.subscription;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SubscriptionRequest(@Positive
                                  @NotNull
                                  Long userId,

                                  @Positive
                                  @NotNull
                                  Long channelId) {
}
