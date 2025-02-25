package com.netcracker.library.model;

import java.util.Date;

public record SaveBorrowingRequest(
    Long bookId,
    Long memberId,
    Date borrowDate,
    Date returnDate
) {}