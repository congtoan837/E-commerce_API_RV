package com.poly.services;

import java.util.*;

import com.poly.dto.request.SignupRequest;
import com.poly.ex.content.ERole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.dto.request.UserRequest;
import com.poly.dto.response.PageResponse;
import com.poly.dto.response.user.UserResponse;
import com.poly.entity.Role;
import com.poly.entity.User;
import com.poly.exception.AppException;
import com.poly.exception.ErrorCode;
import com.poly.mapper.UserMapper;
import com.poly.repositories.RoleRepository;
import com.poly.repositories.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public UserResponse create(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USERNAME_EXISTS);

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Role> roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse signup(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USERNAME_EXISTS);

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Role> roles = roleRepository.findAllById(
                Collections.singletonList(ERole.USER.name())
        );
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public PageResponse<UserResponse> get(String keyword, int page, int size) {
        if (StringUtils.isNotBlank(keyword))
            keyword = StringUtils.lowerCase(keyword).trim();

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<User> pageResult = userRepository.searchByKeywordWithoutUsername("admin", keyword, pageable);
        return userMapper.toPageResponse(pageResult);
    }

    public UserResponse update(UserRequest request) {
        User user =
                userRepository.findById(request.getId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUserFromUserRequest(user, request);

        List<Role> roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void delete(UUID id) {
        if (userRepository.softDelete(id) < 1)
            throw new AppException(ErrorCode.USER_NOT_FOUND);
    }

    public UserResponse getInfo() {
        User user = this.getCurrentUser();
        return userMapper.toUserResponse(user);
    }
}
