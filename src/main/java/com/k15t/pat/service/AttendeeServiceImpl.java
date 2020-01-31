package com.k15t.pat.service;

import com.k15t.pat.exception.UniqueConstraintViolationException;
import com.k15t.pat.model.Attendee;
import com.k15t.pat.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Attendee createAttendee(Attendee attendee) {

        if (attendeeRepository.existsByEmail(attendee.getEmail()))
            throw new UniqueConstraintViolationException("Email already exists.");

        if (attendeeRepository.existsByPhoneNumber(attendee.getPhoneNumber()))
            throw new UniqueConstraintViolationException("Phone number already exists.");

        attendee.setPassword(passwordEncoder.encode(attendee.getPassword()));
        return attendeeRepository.save(attendee);
    }
}
