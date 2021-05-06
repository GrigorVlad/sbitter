package me.grigor.sbitter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_connections")
@Getter
@Setter
@NoArgsConstructor
public class UserConnection extends BaseEntity{

    @Column(
            name = "user_id",
            nullable = false
    )
    private Long userId;

    @Column(
            name = "follower_id",
            nullable = false
    )
    private Long followerId;
}
