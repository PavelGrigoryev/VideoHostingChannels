package ru.clevertec.videohostingchannels.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.clevertec.videohostingchannels.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelFilterResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.model.Subscription;
import ru.clevertec.videohostingchannels.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ChannelMapper {

    @Mapping(target = "author.id", source = "authorId")
    Channel fromRequest(Long authorId, ChannelRequest request);

    @Mapping(target = "author.id", source = "authorId")
    Channel fromRequest(Long id, Long authorId, LocalDateTime createdAt, ChannelRequest request);

    @Mapping(target = "authorId", source = "channel.author.id")
    ChannelResponse toResponse(Channel channel);

    ChannelFilterResponse toFilterInfoResponse(Channel channel, Integer subscribersCount);

    @Mapping(target = "subscribers", source = "channel.subscriptions")
    ChannelDetailedInformationResponse toDetailedInformationResponse(Channel channel);

    @IterableMapping(qualifiedByName = "subscriptionsToSubscribers")
    List<UserResponse> subscriptionsToSubscribers(List<Subscription> subscriptions);

    @Named("subscriptionsToSubscribers")
    default UserResponse subscriptionToSubscriber(Subscription subscription) {
        User user = subscription.getUser();
        return new UserResponse(user.getId(), user.getNickname(), user.getName(), user.getEmail());
    }

}
