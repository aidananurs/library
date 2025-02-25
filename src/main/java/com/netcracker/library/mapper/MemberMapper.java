package com.netcracker.library.mapper;

import com.netcracker.library.entity.Member;
import com.netcracker.library.model.MemberDto;
import com.netcracker.library.model.SaveMemberRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberDto toDto(Member member);
    Member toEntity(MemberDto memberDto);
    Member toEntity(SaveMemberRequest request);
}