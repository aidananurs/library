package com.netcracker.library.model;

import java.time.LocalDate;

public record AuthorInfo(
      Long authorId,
      String name,
      String nationality,
      LocalDate birthDate
) {}
