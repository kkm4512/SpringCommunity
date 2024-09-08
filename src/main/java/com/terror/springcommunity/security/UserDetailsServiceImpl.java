package com.terror.springcommunity.security;

import com.terror.springcommunity.constans.ApiResponseMemberEnum;
import com.terror.springcommunity.exception.MemberException;
import com.terror.springcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(userRepository.findByUsername(username).orElseThrow(() -> new MemberException(ApiResponseMemberEnum.MEMBER_NOT_FOUND)));
    }
}
