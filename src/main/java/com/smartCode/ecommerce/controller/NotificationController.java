package com.smartCode.ecommerce.controller;


import com.smartCode.ecommerce.model.dto.NotificationDto;
import com.smartCode.ecommerce.service.notofication.NotificationService;
import com.smartCode.ecommerce.util.constants.RoleConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notify")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<NotificationDto> create(@RequestBody NotificationDto notificationEntity) {
        return ResponseEntity.ok(notificationService.create(notificationEntity));
    }

    @GetMapping("/ready")
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<List<NotificationDto>> getReady() {
        return ResponseEntity.ok(notificationService.getReady());
    }

    @GetMapping("/waiting")
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<List<NotificationDto>> getWaiting() {
        return ResponseEntity.ok(notificationService.getWaiting());
    }

}