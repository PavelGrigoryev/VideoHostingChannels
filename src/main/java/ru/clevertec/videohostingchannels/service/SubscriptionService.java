package ru.clevertec.videohostingchannels.service;

import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {

    SubscriptionResponse subscribeOn(Long userId, Long channelId);

    SubscriptionResponse subscribeOff(Long userId, Long channelId);

}
