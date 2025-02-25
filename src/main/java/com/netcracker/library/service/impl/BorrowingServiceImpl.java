package com.netcracker.library.service.impl;

import com.netcracker.library.entity.Borrowing;
import com.netcracker.library.exceptions.NotFoundException;
import com.netcracker.library.mapper.BorrowingMapper;
import com.netcracker.library.model.BorrowingDto;
import com.netcracker.library.model.SaveBorrowingRequest;
import com.netcracker.library.repository.BookRepository;
import com.netcracker.library.repository.BorrowingRepository;
import com.netcracker.library.repository.MemberRepository;
import com.netcracker.library.service.BorrowingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper borrowingMapper;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Override
    public void save(SaveBorrowingRequest request) {
        var book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new NotFoundException("Book with id " + request.bookId() + " not found"));
        var member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new NotFoundException("Member with id " + request.memberId() + " not found"));

        var borrowing = borrowingMapper.toEntity(request);
        borrowing.setBook(book);
        borrowing.setMember(member);

        borrowingRepository.save(borrowing);
    }

   @Override
    @Transactional
    public void update(Long id, SaveBorrowingRequest request) {
        Borrowing existingBorrowing = borrowingRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Borrowing with id " + id + " not found"));

        var book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new NotFoundException("Book with id " + request.bookId() + " not found"));
        var member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new NotFoundException("Member with id " + request.memberId() + " not found"));

        existingBorrowing.setBook(book);
        existingBorrowing.setMember(member);
        existingBorrowing.setBorrowDate(request.borrowDate());
        existingBorrowing.setReturnDate(request.returnDate());

        borrowingRepository.save(existingBorrowing);
    }


    @Override
    public Optional<BorrowingDto> getById(Long borrowingId) {
        return borrowingRepository.findById(borrowingId).map(borrowingMapper::toDto);
    }

    @Override
    public List<BorrowingDto> getAll() {
        return borrowingRepository.findAll().stream().map(borrowingMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }
}
