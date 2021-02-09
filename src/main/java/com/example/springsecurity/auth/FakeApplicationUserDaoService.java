package com.example.springsecurity.auth;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.springsecurity.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        STUDENT.getGrantedAuthority(),
                        passwordEncoder.encode("1"),
                        "ann",
                        true,
                        true,
                        true,
                        true
                ),

                new ApplicationUser(
                        ADMIN.getGrantedAuthority(),
                        passwordEncoder.encode("1"),
                        "linda",
                        true,
                        true,
                        true,
                        true
                ),

                new ApplicationUser(
                        ADMINTRAINEE.getGrantedAuthority(),
                        passwordEncoder.encode("1"),
                        "tom",
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
