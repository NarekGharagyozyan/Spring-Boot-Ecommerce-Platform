package com.smartCode.ecommerce.controller;


import com.smartCode.ecommerce.model.dto.card.CardRequestDto;
import com.smartCode.ecommerce.model.dto.card.CardResponseDto;
import com.smartCode.ecommerce.service.card.CardService;
import com.smartCode.ecommerce.util.constants.Path;
import com.smartCode.ecommerce.util.constants.RoleConstants;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(Path.CARDS)
public class CardController {

    private final CardService cardService;

    @PostMapping(Path.CREATE)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto cardRequestDto) {
        CardResponseDto cardResponseDto = cardService.create(cardRequestDto);
        return ResponseEntity.ok(cardResponseDto);
    }

    @GetMapping(Path.FIND_WITH_OWNER_ID)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<List<CardResponseDto>> findByUserId(@PathVariable @Positive Integer ownerId) {
        List<CardResponseDto> byUserId = cardService.findByOwnerId(ownerId);
        return ResponseEntity.ok(byUserId);
    }

    @DeleteMapping(Path.DELETE_WITH_OWNER_ID)
    @PreAuthorize("hasRole('" + RoleConstants.ADMIN_ROLE + "')")
    public ResponseEntity<Void> deleteAllByOwnerId(@PathVariable @Positive Integer ownerId) {
        cardService.deleteAllByOwnerId(ownerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(Path.DELETE_WITH_CARD_ID)
    @PreAuthorize("hasRole('" + RoleConstants.USER_ROLE + "')")
    public ResponseEntity<Void> deleteByCardId(@PathVariable @Positive Integer cardId) {
        cardService.deleteByCardId(cardId);
        return ResponseEntity.ok().build();
    }
}