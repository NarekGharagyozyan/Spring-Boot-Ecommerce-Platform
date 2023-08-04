package com.smartCode.ecommerce.controller;

import com.smartCode.ecommerce.model.dto.user.UserAuthDto;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.dto.user.UserUpdateDto;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.service.user.UserService;
import com.smartCode.ecommerce.util.constants.Path;
import com.smartCode.ecommerce.util.constants.RoleConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(Path.USERS)
public class AccountController {

    private final UserService userService;

    @PostMapping(Path.REGISTER)
    public ResponseEntity<UserResponseDto> registration(@RequestBody @Valid UserRequestDto user) {
        UserResponseDto userResponseDto = userService.register(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping(Path.LOGIN)
    public ResponseEntity<UserAuthDto> login(@RequestParam @NotBlank String username,
                                             @RequestParam @NotBlank String password) {

        return ResponseEntity.ok(userService.login(username, password));
    }

    @GetMapping(Path.FIND)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<UserResponseDto> getById(@PathVariable @Positive Integer id) {
        UserResponseDto userResponseDto = userService.getById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping(Path.FIND_ALL)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }


    @PatchMapping(Path.UPDATE)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<UserResponseDto> updateUserPartial(@PathVariable @Positive Integer id,
                                                      @RequestBody @Valid UserUpdateDto userUpdateDto) {
        UserResponseDto userResponseDto = userService.update(id, userUpdateDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping(Path.DELETE)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<Void> deleteUser(@PathVariable @Positive Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(Path.VERIFY)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<Void> verifyUser(@RequestParam @Email String email,
                                              @RequestParam @Size(min = 6, max = 6) String code) {
        userService.verify(email, code);
        return ResponseEntity.ok().build();
    }

    @PostMapping(Path.CHANGE_PASSWORD)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<Void> changePassword(@RequestParam @Email String email,
                                              @RequestParam @NotBlank String password,
                                              @RequestParam @NotBlank String newPassword,
                                              @RequestParam @NotBlank String newRepeatPassword) {

        userService.changePassword(email, password, newPassword, newRepeatPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping(Path.FILTER)
    public ResponseEntity<List<UserResponseDto>> filter(@RequestBody @Valid FilterSearchUser.Filter userFilter) {
        List<UserResponseDto> userResponseDtoList = userService.filter(userFilter);
        return ResponseEntity.ok(userResponseDtoList);
    }

    @PostMapping(Path.SEARCH)
    public ResponseEntity<List<UserResponseDto>> search(@RequestBody @Valid FilterSearchUser.Search text) {
        List<UserResponseDto> userResponseDtoList = userService.search(text);
        return ResponseEntity.ok(userResponseDtoList);
    }
}
