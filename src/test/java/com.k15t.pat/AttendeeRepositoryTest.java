package com.k15t.pat;

import com.k15t.pat.model.Attendee;
import com.k15t.pat.repository.AttendeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationBootstrap.class})
public class AttendeeRepositoryTest {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Test
    public void whenAttendeePersists_thenIdHasValue() {
        Attendee attendee = new Attendee();
        attendee.setName("Nariman");
        attendee.setPassword("12345678A");
        attendee.setEmail("n.esmaielyfard@gmail.com");
        attendee.setAddress("Tehran - Vali asr street");
        attendee.setPhoneNumber("+989368839369");

        Attendee createdAttendee = attendeeRepository.save(attendee);

        assertNotNull(createdAttendee.getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void whenNameIsNull_thenThrowsDataIntegrityViolationException() {
        Attendee attendee = new Attendee();
        attendee.setPassword("12345678A");
        attendee.setEmail("n.esmaielyfard@gmail.com");
        attendee.setAddress("Tehran - Vali asr street");
        attendee.setPhoneNumber("+989368830369");

        Attendee createdAttendee = attendeeRepository.save(attendee);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void whenPasswordIsNull_thenThrowsDataIntegrityViolationException() {
        Attendee attendee = new Attendee();
        attendee.setName("Nariman");
        attendee.setEmail("n.esmaielyfard@gmail.com");
        attendee.setAddress("Tehran - Vali asr street");
        attendee.setPhoneNumber("+989368830369");

        Attendee createdAttendee = attendeeRepository.save(attendee);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void whenEmailIsNull_thenThrowsDataIntegrityViolationException() {
        Attendee attendee = new Attendee();
        attendee.setName("Nariman");
        attendee.setPassword("12345678A");
        attendee.setAddress("Tehran - Vali asr street");
        attendee.setPhoneNumber("+989368830369");

        Attendee createdAttendee = attendeeRepository.save(attendee);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void whenAddressIsNull_thenThrowsDataIntegrityViolationException() {
        Attendee attendee = new Attendee();
        attendee.setName("Nariman");
        attendee.setPassword("12345678A");
        attendee.setEmail("n.esmaielyfard@gmail.com");
        attendee.setPhoneNumber("+989368830369");

        Attendee createdAttendee = attendeeRepository.save(attendee);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void whenEmailIsDuplicate_thenThrowsDataIntegrityViolationException() {
        Attendee firstAttendee = new Attendee();
        firstAttendee.setName("Nariman");
        firstAttendee.setPassword("12345678A");
        firstAttendee.setEmail("s.shams@gmail.com");
        firstAttendee.setAddress("Tehran - Vali asr street");
        firstAttendee.setPhoneNumber("+989113441540");

        Attendee secondAttendee = new Attendee();
        secondAttendee.setName("Nariman");
        secondAttendee.setPassword("12345678A");
        secondAttendee.setEmail("s.shams@gmail.com");
        secondAttendee.setAddress("Tehran - Vali asr street");
        secondAttendee.setPhoneNumber("+989100676100");

        Attendee createdAttendee = attendeeRepository.save(firstAttendee);
        Attendee duplicateAttendee = attendeeRepository.save(secondAttendee);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void whenPhoneNumberIsDuplicate_thenThrowsDataIntegrityViolationException() {
        Attendee firstAttendee = new Attendee();
        firstAttendee.setName("Nariman");
        firstAttendee.setPassword("12345678A");
        firstAttendee.setEmail("s.shams@gmail.com");
        firstAttendee.setAddress("Tehran - Vali asr street");
        firstAttendee.setPhoneNumber("+989100676100");

        Attendee secondAttendee = new Attendee();
        secondAttendee.setName("Nariman");
        secondAttendee.setPassword("12345678A");
        secondAttendee.setEmail("k15t@gmail.com");
        secondAttendee.setAddress("Tehran - Vali asr street");
        secondAttendee.setPhoneNumber("+989100676100");

        Attendee createdAttendee = attendeeRepository.save(firstAttendee);
        Attendee duplicateAttendee = attendeeRepository.save(secondAttendee);
    }
}
