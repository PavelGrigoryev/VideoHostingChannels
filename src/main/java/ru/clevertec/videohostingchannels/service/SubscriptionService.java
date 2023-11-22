package ru.clevertec.videohostingchannels.service;

import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionRequest;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {

    SubscriptionResponse subscribeOn(SubscriptionRequest request);

}
