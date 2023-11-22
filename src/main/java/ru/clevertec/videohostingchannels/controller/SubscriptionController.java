package ru.clevertec.videohostingchannels.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionRequest;
import ru.clevertec.videohostingchannels.dto.subscription.SubscriptionResponse;
import ru.clevertec.videohostingchannels.service.SubscriptionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> subscribeOn(@RequestBody SubscriptionRequest request) {
        return ResponseEntity.ok(subscriptionService.subscribeOn(request));
    }

    @DeleteMapping
    public ResponseEntity<SubscriptionResponse> subscribeOff(@RequestBody SubscriptionRequest request) {
        return ResponseEntity.ok(subscriptionService.subscribeOff(request));
    }

}
