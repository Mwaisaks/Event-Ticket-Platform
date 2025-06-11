package com.devtiro.EventTicketPlatform.service;

import com.devtiro.EventTicketPlatform.domain.entity.Event;
import com.devtiro.EventTicketPlatform.domain.request.CreateEventRequest;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organiserId, CreateEventRequest event);
}
