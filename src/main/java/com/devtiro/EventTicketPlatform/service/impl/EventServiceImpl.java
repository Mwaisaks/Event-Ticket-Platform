package com.devtiro.EventTicketPlatform.service.impl;

import com.devtiro.EventTicketPlatform.domain.entity.Event;
import com.devtiro.EventTicketPlatform.domain.entity.TicketType;
import com.devtiro.EventTicketPlatform.domain.entity.User;
import com.devtiro.EventTicketPlatform.domain.dtos.request.CreateEventRequest;
import com.devtiro.EventTicketPlatform.repository.EventRepository;
import com.devtiro.EventTicketPlatform.repository.UserRepository;
import com.devtiro.EventTicketPlatform.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository; //to look up the organizer id
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organiserId, CreateEventRequest event) {

        User organizer = userRepository.findById(organiserId)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with ID '%S' not found", organiserId)
                ));

        Event createdEvent = new Event();

        List<TicketType> createdTicketTypes = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType createdTicketType = new TicketType();
                    createdTicketType.setName(ticketType.getName());
                    createdTicketType.setPrice(ticketType.getPrice());
                    createdTicketType.setDescription(ticketType.getDescription());
                    createdTicketType.setTotalAvailable(ticketType.getTotalAvailable());
                    createdTicketType.setEvent(createdEvent);
                    return createdTicketType;
                }
        ).toList();


        createdEvent.setName(event.getName());
        createdEvent.setStart(event.getStart());
        createdEvent.setEnd(event.getEnd());
        createdEvent.setVenue(event.getVenue());
        createdEvent.setSalesStart(event.getSalesStart());
        createdEvent.setSalesEnd(event.getSalesEnd());
        createdEvent.setStatus(event.getStatus());
        createdEvent.setOrganizer(organizer);
        createdEvent.setTicket_types(createdTicketTypes);

        return eventRepository.save(createdEvent);
    }
}
