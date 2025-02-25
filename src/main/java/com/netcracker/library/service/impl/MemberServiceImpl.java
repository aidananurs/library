package com.netcracker.library.service.impl;

import com.netcracker.library.entity.Member;
import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.mapper.MemberMapper;
import com.netcracker.library.model.MemberDto;
import com.netcracker.library.model.SaveMemberRequest;
import com.netcracker.library.repository.MemberRepository;
import com.netcracker.library.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public void save(SaveMemberRequest request) {
        Member member = memberMapper.toEntity(request);
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void update(Long id, SaveMemberRequest request) {
        Member existingMember = memberRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Member with id " + id + " not found"));

        existingMember.setName(request.name());
        existingMember.setPhoneNumber(request.phoneNumber());
        existingMember.setEmail(request.email());

        memberRepository.save(existingMember);
    }


    @Override
    public Optional<MemberDto> getById(Long memberId) {
        return memberRepository.findById(memberId).map(memberMapper::toDto);
    }

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream().map(memberMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
