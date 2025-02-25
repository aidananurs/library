
package com.netcracker.library.service;

import com.netcracker.library.model.BorrowingDto;
import com.netcracker.library.model.SaveBorrowingRequest;

import java.util.List;
import java.util.Optional;

public interface BorrowingService {
    void save(SaveBorrowingRequest borrowingDto);
    void update(Long id, SaveBorrowingRequest borrowingDto);
    Optional<BorrowingDto> getById(Long borrowingId);
    List<BorrowingDto> getAll();
    void deleteById(Long borrowingId);
}
