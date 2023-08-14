package com.smartCode.ecommerce.controller;


import com.smartCode.ecommerce.model.dto.notification.NotificationRequestDto;
import com.smartCode.ecommerce.model.dto.notification.NotificationResponseDto;
import com.smartCode.ecommerce.service.notification.NotificationService;
import com.smartCode.ecommerce.util.constants.Path;
import com.smartCode.ecommerce.util.constants.RoleConstants;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(Path.NOTIFY)
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping(Path.CREATE)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<NotificationResponseDto> create(@RequestBody @Valid NotificationRequestDto notificationRequestDto) {
        NotificationResponseDto notificationResponseDto = notificationService.create(notificationRequestDto);
        return ResponseEntity.ok(notificationResponseDto);
    }

    @GetMapping(Path.READY)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<List<NotificationResponseDto>> getReady() {
        List<NotificationResponseDto> readyNotifications = notificationService.getReady();
        return ResponseEntity.ok(readyNotifications);
    }

    @GetMapping(Path.WAITING)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<List<NotificationResponseDto>> getWaiting() {
        List<NotificationResponseDto> readyNotifications = notificationService.getWaiting();
        return ResponseEntity.ok(readyNotifications);
    }

}