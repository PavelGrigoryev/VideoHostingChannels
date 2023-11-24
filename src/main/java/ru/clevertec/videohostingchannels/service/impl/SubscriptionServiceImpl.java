package ru.clevertec.videohostingchannels.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionRequest;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionResponse;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionStatus;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
import ru.clevertec.videohostingchannels.mapper.SubscriptionMapper;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.model.Subscription;
import ru.clevertec.videohostingchannels.model.User;
import ru.clevertec.videohostingchannels.repository.ChannelRepository;
import ru.clevertec.videohostingchannels.repository.SubscriptionRepository;
import ru.clevertec.videohostingchannels.repository.UserRepository;
import ru.clevertec.videohostingchannels.service.SubscriptionService;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final ChannelRepository channelRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    @Transactional
    public SubscriptionResponse subscribeOn(SubscriptionRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException("User with id %s is not found".formatted(request.userId())));
        Channel channel = channelRepository.findById(request.channelId())
                .orElseThrow(() -> new NotFoundException("Channel with id %s is not found".formatted(request.channelId())));
        Subscription subscription = subscriptionRepository.save(subscriptionMapper.toSubscription(user, channel));
        return subscriptionMapper.toResponse(subscription, SubscriptionStatus.ENABLED);
    }

    @Override
    @Transactional
    public SubscriptionResponse subscribeOff(SubscriptionRequest request) {
        return subscriptionRepository.findByIdWithUserAndChannel(request.userId(), request.channelId())
                .map(subscription -> {
                    subscriptionRepository.delete(subscription);
                    return subscription;
                })
                .map(subscription -> subscriptionMapper.toResponse(subscription, SubscriptionStatus.DISABLED))
                .orElseThrow(() -> new NotFoundException("Subscription with user_id %s and channel_id %s is not found"
                        .formatted(request.userId(), request.channelId())));
    }

}
