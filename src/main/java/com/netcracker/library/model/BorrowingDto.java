package com.netcracker.library.model;

import java.util.Date;

public record BorrowingDto(
    Long borrowingId,
    Long bookId,
    Long memberId,
    Date borrowDate,
    Date returnDate
) {}