package ru.clevertec.videohostingchannels.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscriptionId implements Serializable {

    private Long user;
    private Long channel;

}
