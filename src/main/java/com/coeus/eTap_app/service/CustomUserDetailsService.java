package com.coeus.eTap_app.service;

import com.coeus.eTap_app.domain.model.Company;
import com.coeus.eTap_app.domain.model.User;
import com.coeus.eTap_app.repository.CompanyRepository;
import com.coeus.eTap_app.repository.UserRepository;
import com.coeus.eTap_app.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findUserByUserEmail(email));
        Optional<Company> company = companyRepository.findByCompanyEmail(email);

        if (user.isPresent() && company.isPresent()) {
            throw new IllegalStateException("Email exists in both user and company tables");
        }

        if (user.isPresent()) {
            return new CustomUserDetails(
                    user.get().getUserEmail(),
                    user.get().getUserPassword(),
                    "USER"
            );
        }

        if (company.isPresent()) {
            return new CustomUserDetails(
                    company.get().getCompanyEmail(),
                    company.get().getCompanyPassword(),
                    "COMPANY"
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}