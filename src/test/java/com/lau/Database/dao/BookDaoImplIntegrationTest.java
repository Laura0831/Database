package com.lau.Database.dao;


import com.lau.Database.TestDataUtil;
import com.lau.Database.dao.impl.AuthorDaoImpl;
import com.lau.Database.dao.impl.BookDaoImpl;
import com.lau.Database.domain.Author;
import com.lau.Database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTest {

    private BookDaoImpl underTest;
    private AuthorDAO author;

    // The autowired annotation tells spring to inject dependencies as declared in this constructor
    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDAO author){
        this.underTest = underTest;
        this.author = author;
    }

    @Test
    public void testBookCreation(){

        Author authorTemp = TestDataUtil.createTestAuthor(); //we got to create an author to be allowed access to the foreign key that is connected to the author
        author.create(authorTemp); //creates the author in the database
        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(authorTemp.getId()); //interconnects the author with the book
        underTest.create(book);
        Optional<Book> result =  underTest.find(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }


}
