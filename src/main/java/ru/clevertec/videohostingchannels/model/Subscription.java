package ru.clevertec.videohostingchannels.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "subscriptions")
@IdClass(SubscriptionId.class)
public class Subscription {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Channel channel;

}
