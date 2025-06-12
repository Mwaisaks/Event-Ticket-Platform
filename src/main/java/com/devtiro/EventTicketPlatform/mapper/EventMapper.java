package com.devtiro.EventTicketPlatform.mapper;

import com.devtiro.EventTicketPlatform.domain.entity.Event;
import com.devtiro.EventTicketPlatform.domain.request.CreateEventRequest;
import com.devtiro.EventTicketPlatform.domain.request.CreateEventRequestDto;
import com.devtiro.EventTicketPlatform.domain.request.CreateTicketTypeRequest;
import com.devtiro.EventTicketPlatform.domain.request.CreateTicketTypeRequestDto;
import com.devtiro.EventTicketPlatform.domain.response.CreateEventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE) //ignore everything you can't map
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto(CreateEventRequestDto dto);
    CreateEventResponseDto toDto(Event event);
}
