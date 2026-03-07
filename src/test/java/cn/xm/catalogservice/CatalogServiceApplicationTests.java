package cn.xm.catalogservice;

import cn.xm.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void whenPostRequestThenBook() {
        var expectedBook = new Book("1231231231", "Title", "Author", 9.90);
        webTestClient.post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(book ->{
                    assertThat(book).isNotNull();
                    assertThat(book.isbn()).isEqualTo(expectedBook.isbn());
                });
    }

}
