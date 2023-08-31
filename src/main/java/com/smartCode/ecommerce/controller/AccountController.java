package com.smartCode.ecommerce.controller;

import com.smartCode.ecommerce.model.dto.user.UserAuthDto;
import com.smartCode.ecommerce.model.dto.user.UserLoginDto;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.service.user.UserService;
import com.smartCode.ecommerce.util.constants.Path;
import com.smartCode.ecommerce.util.constants.RoleConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(Path.ACCOUNTS)
public class AccountController {

    private final UserService userService;

    @PostMapping(Path.REGISTER)
    public ResponseEntity<UserResponseDto> registration(@RequestBody @Valid UserRequestDto user) {
        UserResponseDto userResponseDto = userService.register(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping(Path.LOGIN)
    public ResponseEntity<UserAuthDto> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto.getUsername(), userLoginDto.getPassword()));
    }

    @GetMapping(Path.LOGOUT)
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping(Path.VERIFY)
    public ResponseEntity<Void> verifyUser(@RequestParam @Email String email,
                                           @RequestParam @Size(min = 6, max = 6) String code) {
        userService.verify(email, code);
        return ResponseEntity.ok().build();
    }
}
