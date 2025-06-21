package com.poly.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.dto.request.RoleRequest;
import com.poly.dto.response.user.RoleResponse;
import com.poly.entity.Role;
import com.poly.mapper.RoleMapper;
import com.poly.repositories.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public List<RoleResponse> get() {
        return roleMapper.toRoleResponseList(roleRepository.findAll());
    }

    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }
}
