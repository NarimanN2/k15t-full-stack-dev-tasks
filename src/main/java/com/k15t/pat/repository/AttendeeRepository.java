package com.k15t.pat.repository;

import com.k15t.pat.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    @Query("select case when count(a) > 0 then true else false end from Attendee a where a.email = ?1")
    boolean existsByEmail(String email);

    @Query("select case when count(a) > 0 then true else false end from Attendee a where a.phoneNumber = ?1")
    boolean existsByPhoneNumber(String phoneNumber);
}
