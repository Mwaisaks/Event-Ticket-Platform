package com.devtiro.EventTicketPlatform.service;

import com.devtiro.EventTicketPlatform.domain.entity.Event;
import com.devtiro.EventTicketPlatform.domain.dtos.request.CreateEventRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organiserId, CreateEventRequest event);

    Page<Event> listEventForOrganizer(UUID organizerId, Pageable pageable);
}
