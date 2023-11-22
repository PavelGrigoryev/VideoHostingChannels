package ru.clevertec.videohostingchannels.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionResponse;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionStatus;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.model.Subscription;
import ru.clevertec.videohostingchannels.model.User;

@Mapper
public interface SubscriptionMapper {

    Subscription toSubscription(User user, Channel channel);

    @Mapping(target = "userNickname", source = "subscription.user.nickname")
    @Mapping(target = "channelName", source = "subscription.channel.name")
    SubscriptionResponse toResponse(Subscription subscription, SubscriptionStatus subscriptionStatus);

}
