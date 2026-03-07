package cn.xm.catalogservice.web;

import cn.xm.catalogservice.domain.Book;
import cn.xm.catalogservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class BookJsonTest {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1231231231", "Title", "Author", 9.90);
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
    }


    @Test
    void testDeserialize() throws Exception {
        var jsonContent = """
                {
                "isbn": "1231231231",
                "title": "Title",
                "author": "Author",
                "price": "9.90"
                }
                """;
        assertThat(json.parse(jsonContent)).usingRecursiveComparison()
                .isEqualTo(new Book("1231231231", "Title", "Author", 9.90));
    }
}
