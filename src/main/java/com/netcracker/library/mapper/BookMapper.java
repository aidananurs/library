package com.netcracker.library.mapper;

import com.netcracker.library.entity.Author;
import com.netcracker.library.entity.Book;
import com.netcracker.library.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(SaveBookRequest request);
    Book toEntity(BookDto bookDto);

    @Mapping(target = "authors", source = "authors", qualifiedByName = "mapAuthors")
    @Mapping(target = "categoryId", source = "category.categoryId")
    BookDto toDto(Book book);

    @Named("mapAuthors")
    default List<AuthorInfo> mapAuthors(Set<Author> authors) {
        return (authors != null) ? authors.stream().map(this::toAuthorInfo).collect(Collectors.toList()) : List.of();
    }

    AuthorInfo toAuthorInfo(Author author);

}
