package com.netcracker.library.service;

import com.netcracker.library.model.MemberDto;
import com.netcracker.library.model.SaveMemberRequest;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void save(SaveMemberRequest memberDto);
    void update(Long id, SaveMemberRequest memberDto);
    Optional<MemberDto> getById(Long memberId);
    List<MemberDto> getAll();
    void deleteById(Long memberId);
}
