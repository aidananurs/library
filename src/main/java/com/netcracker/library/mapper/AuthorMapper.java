package com.netcracker.library.mapper;

import com.netcracker.library.entity.Author;
import com.netcracker.library.entity.Book;
import com.netcracker.library.model.AuthorDto;
import com.netcracker.library.model.BookDto;
import com.netcracker.library.model.BookInfo;
import com.netcracker.library.model.SaveAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
     @Mapping(target = "books", source = "books", qualifiedByName = "mapBooks")
     AuthorDto toDto(Author author);
     Author toEntity(AuthorDto authorDto);
     Author toEntity(SaveAuthorRequest request);

    @Named("mapBooks")
    default List<BookInfo> mapBooks(List<Book> books) {
        return (books != null) ? books.stream().map(this::toBookInfo).collect(Collectors.toList()) : List.of();
    }

    BookInfo toBookInfo(Book book);

}
