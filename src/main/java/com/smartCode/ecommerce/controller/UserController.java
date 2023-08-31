package com.smartCode.ecommerce.controller;

import com.smartCode.ecommerce.model.dto.user.UserAuthDto;
import com.smartCode.ecommerce.model.dto.user.UserChangePasswordDto;
import com.smartCode.ecommerce.model.dto.user.UserLoginDto;
import com.smartCode.ecommerce.model.dto.user.UserRequestDto;
import com.smartCode.ecommerce.model.dto.user.UserResponseDto;
import com.smartCode.ecommerce.model.dto.user.UserUpdateDto;
import com.smartCode.ecommerce.model.dto.user.filterAndSearch.FilterSearchUser;
import com.smartCode.ecommerce.service.user.UserService;
import com.smartCode.ecommerce.util.constants.Path;
import com.smartCode.ecommerce.util.constants.RoleConstants;
import com.smartCode.ecommerce.util.currentUser.CurrentUser;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(Path.USERS)
public class UserController {

    private final UserService userService;

    @GetMapping(Path.ID)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<UserResponseDto> getById(@PathVariable @Positive Integer id) {
        UserResponseDto userResponseDto = userService.getById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PatchMapping
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<UserResponseDto> updateUserPartial(@RequestBody @Valid UserUpdateDto userUpdateDto) {
        UserResponseDto userResponseDto = userService.update(CurrentUser.getId(), userUpdateDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping(Path.ID)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<Void> deleteUser(@PathVariable @Positive Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PatchMapping(Path.CHANGE_PASSWORD)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid UserChangePasswordDto userChangePasswordDto) {

        userService.changePassword(userChangePasswordDto.getPassword(),
                userChangePasswordDto.getChangePassword(),
                userChangePasswordDto.getRepeatPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping(Path.FILTER)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<List<UserResponseDto>> filter(@RequestBody @Valid FilterSearchUser.Filter userFilter) {
        List<UserResponseDto> userResponseDtoList = userService.filter(userFilter);
        return ResponseEntity.ok(userResponseDtoList);
    }

    @PostMapping(Path.SEARCH)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<List<UserResponseDto>> search(@RequestBody @Valid FilterSearchUser.Search text) {
        List<UserResponseDto> userResponseDtoList = userService.search(text);
        return ResponseEntity.ok(userResponseDtoList);
    }
}
