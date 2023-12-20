package ru.clevertec.videohostingchannels.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionResponse;
import ru.clevertec.videohostingchannels.service.SubscriptionService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/{userId}/subscriptions/{channelId}")
    public ResponseEntity<SubscriptionResponse> subscribeOn(@PathVariable @Positive Long userId,
                                                            @PathVariable @Positive Long channelId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.subscribeOn(userId, channelId));
    }

    @DeleteMapping("/{userId}/subscriptions/{channelId}")
    public ResponseEntity<SubscriptionResponse> subscribeOff(@PathVariable @Positive Long userId,
                                                             @PathVariable @Positive Long channelId) {
        return ResponseEntity.ok(subscriptionService.subscribeOff(userId, channelId));
    }

}
