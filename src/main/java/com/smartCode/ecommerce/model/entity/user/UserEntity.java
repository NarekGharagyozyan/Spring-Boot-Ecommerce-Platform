package com.smartCode.ecommerce.model.entity.user;

import com.smartCode.ecommerce.model.dto.card.CardResponseDto;
import com.smartCode.ecommerce.model.entity.BaseEntity;
import com.smartCode.ecommerce.model.entity.role.RoleEntity;
import com.smartCode.ecommerce.util.constants.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    private String code;

    private Boolean isVerified = false;

    private String middleName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(optional = false)
    private RoleEntity role;


}
