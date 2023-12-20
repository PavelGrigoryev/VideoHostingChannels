package ru.clevertec.videohostingchannels.dto.channel;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelRequest(@NotBlank
                             @Size(max = 40)
                             String name,

                             @NotBlank
                             @Size(max = 500)
                             String shortDescription,

                             @Pattern(regexp = "Russian|English|Spanish|French|Chinese|Japanese|Korean|Italian|Turkish|German",
                                     message = "Acceptable languages are only: Russian, English, Spanish, French," +
                                               " Chinese, Japanese, Korean, Italian, Turkish and German")
                             String mainLanguage,

                             @NotBlank
                             @Size(max = 20)
                             String category) {
}
