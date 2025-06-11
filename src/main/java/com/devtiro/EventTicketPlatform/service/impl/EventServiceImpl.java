package com.devtiro.EventTicketPlatform.service.impl;

import com.devtiro.EventTicketPlatform.domain.entity.Event;
import com.devtiro.EventTicketPlatform.domain.request.CreateEventRequest;
import com.devtiro.EventTicketPlatform.repository.EventRepository;
import com.devtiro.EventTicketPlatform.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organiserId, CreateEventRequest event) {

        Event createdEvent = new Event();

        event.setName(event.getName());
        event.setStart(event.getEnd());
        event.setEnd(event.getStart());
        event.setVenue(event.getVenue());
        event.setSalesStart(event.getSalesStart());
        event.setSalesEnd(event.getSalesEnd());
        event.setStatus(event.getStatus());
        event.setTicketTypes(event.getTicketTypes());

        return eventRepository.save(event);
    }
}
