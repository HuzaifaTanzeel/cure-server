package com.example.demo.CommonFramework.Repositories.Registration;

import com.example.demo.CommonFramework.Model.RegistrationRequest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer> {
    Optional<RegistrationRequest> findByRequestId(int requestId);
    RegistrationRequest findByEmail(String email);

    Optional<RegistrationRequest> findTopByOrderByOtpExpiryDateTimeDesc();
}
