package ru.clevertec.videohostingchannels.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionId implements Serializable {

    private Long user;
    private Long channel;

}
