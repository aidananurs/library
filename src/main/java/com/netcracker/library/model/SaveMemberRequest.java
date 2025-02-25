package com.netcracker.library.model;

public record SaveMemberRequest(
    String name,
    String phoneNumber,
    String email
) {}