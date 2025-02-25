package com.netcracker.library.model;

public record MemberDto(
    Long memberId,
    String name,
    String phoneNumber,
    String email
) {}