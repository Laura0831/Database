package com.lau.Database.dao;

import com.lau.Database.domain.Book;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BookDAO{
    void create(Book book);

    Optional<Book> find(String isbn);
}
