package cn.xm.catalogservice.web;

import cn.xm.catalogservice.exception.BookNotFoundException;
import cn.xm.catalogservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
public class BookControllerMvcTest {
    @MockitoBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "1230937373";
        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }
}
