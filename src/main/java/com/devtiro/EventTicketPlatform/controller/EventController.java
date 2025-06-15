package com.devtiro.EventTicketPlatform.controller;

import com.devtiro.EventTicketPlatform.domain.entity.Event;
import com.devtiro.EventTicketPlatform.domain.dtos.request.CreateEventRequest;
import com.devtiro.EventTicketPlatform.domain.dtos.request.CreateEventRequestDto;
import com.devtiro.EventTicketPlatform.domain.dtos.response.CreateEventResponseDto;
import com.devtiro.EventTicketPlatform.mapper.EventMapper;
import com.devtiro.EventTicketPlatform.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/events")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto){

        //Convert CreateEventRequestDto to CreateEventRequest to be used in the service layer
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);

        UUID userId = UUID.fromString(jwt.getSubject()); //future implementation for the organiser to specify other users who can call this method
        //here we assume the caller is the organiser

        Event createdEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }
}
