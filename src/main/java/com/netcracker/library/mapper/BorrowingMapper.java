package com.netcracker.library.mapper;

import com.netcracker.library.entity.Borrowing;
import com.netcracker.library.model.BorrowingDto;
import com.netcracker.library.model.SaveBorrowingRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { BookMapper.class, MemberMapper.class })
public interface BorrowingMapper {
    @Mapping(source = "book.bookId", target = "bookId")
    @Mapping(source = "member.memberId", target = "memberId")
    BorrowingDto toDto(Borrowing borrowing);

    Borrowing toEntity(BorrowingDto borrowingDto);
    Borrowing toEntity(SaveBorrowingRequest request);
}
