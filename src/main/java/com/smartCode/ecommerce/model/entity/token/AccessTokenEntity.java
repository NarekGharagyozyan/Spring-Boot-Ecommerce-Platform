package com.smartCode.ecommerce.model.entity.token;

import com.smartCode.ecommerce.model.entity.user.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "access_token")
public class AccessTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(optional = false)
    UserEntity user;

    @Column(unique = true, nullable = false)
    String token;
}
