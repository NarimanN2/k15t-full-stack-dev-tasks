package com.k15t.pat.registration;

import com.k15t.pat.dto.AttendeeDto;
import com.k15t.pat.model.Attendee;
import com.k15t.pat.service.AttendeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
public class RegistrationResource {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AttendeeService attendeeService;

    @RequestMapping(value = "/attendees", method = RequestMethod.POST)
    public ResponseEntity<AttendeeDto> createAttendee(@Valid @RequestBody AttendeeDto attendeeDto) {
        Attendee attendee = modelMapper.map(attendeeDto, Attendee.class);
        Attendee createdAttendee = attendeeService.createAttendee(attendee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAttendee.getId()).toUri();
        AttendeeDto createdBudgetDto = modelMapper.map(createdAttendee, AttendeeDto.class);
        return ResponseEntity.created(location).body(createdBudgetDto);
    }
}
