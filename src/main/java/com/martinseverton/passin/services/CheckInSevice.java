package com.martinseverton.passin.services;

import com.martinseverton.passin.domain.attendee.Attendee;
import com.martinseverton.passin.domain.checkin.CheckIn;
import com.martinseverton.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import com.martinseverton.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInSevice {

    private final CheckinRepository checkinRepository;

    public void registerCheckIn(Attendee attendee){
        this.verifyCheckInExists(attendee.getId());

        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setCreatedAt(LocalDateTime.now());
        newCheckIn.setAttendee(attendee);
        this.checkinRepository.save(newCheckIn);
    }

    private void verifyCheckInExists(String attendeeId){
        Optional<CheckIn> isCheckedIn = this.getCheckIn(attendeeId);
        if(isCheckedIn.isPresent()){
            throw new CheckInAlreadyExistsException("Attendee already checked in!");
        }
    }

    public Optional<CheckIn> getCheckIn(String attendeeId){
        return this.checkinRepository.findByAttendeeId(attendeeId);
    }
}
