package com.seahere.backend.alarm.entity;

import com.seahere.backend.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "AlarmToken")
public class AlarmTokenEntity {

    @Id @GeneratedValue
    private Long AlarmTokenId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String token;

    public void tokenUpdate(String token){
        this.token = token;
    }
}
