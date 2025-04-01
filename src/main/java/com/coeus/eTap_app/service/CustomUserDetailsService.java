package com.coeus.eTap_app.service;

import com.coeus.eTap_app.model.Company;
import com.coeus.eTap_app.model.User;
import com.coeus.eTap_app.repository.CompanyRepository;
import com.coeus.eTap_app.repository.UserRepository;
import com.coeus.eTap_app.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserEmail(email);
        if(user != null) {
            return new CustomUserDetails(
                    user.getUserEmail(),
                    user.getUserPassword(),
                    "USER"
            );
        }

        Company company = companyRepository.findByCompanyEmail(email);
        if(company != null) {
            return new CustomUserDetails(
                    company.getCompanyEmail(),
                    company.getCompanyPassword(),
                    "COMPANY"
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}