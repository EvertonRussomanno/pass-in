package com.martinseverton.passin.dto.event;

public record EventDetailDTO(String id,
                             String title,
                             String details,
                             String Slug,
                             Integer maximumAttendees,
                             Integer attendeesAmount) {
}
