package com.poly.ex.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum ERole {
    ADMIN("ADMIN", Set.of("CREATE", "READ", "UPDATE", "DELETE")),
    STAFF("STAFF", Set.of("READ", "UPDATE")),
    USER("USER", Set.of("READ"));

    private String name;
    private Set<String> permissions;
}
