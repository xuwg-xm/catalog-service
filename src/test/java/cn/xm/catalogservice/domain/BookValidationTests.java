package cn.xm.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BookValidationTests {
    private static Validator validator;

    /**
     * 类中所有测试运行之前要执行的代码块
     */
    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldCorrectAndValidationSucceeds() {
        var book = new Book(
                "1234567890",
                "三字经",
                "zhangsan",
                123.00
        );
        Set<ConstraintViolation<Book>> validation = validator.validate(book);
        assertThat(validation).isEmpty();
    }

    @Test
    void whenIsbnDefinedButInCorrectThenValidationFails() {
        var book = new Book(
                "123456780",
                "三字经",
                "zhangsan",
                123.00
        );
        Set<ConstraintViolation<Book>> validation = validator.validate(book);
        assertThat(validation).hasSize(1);
        assertThat(validation
                .iterator()
                .next().getMessage())
                    .isEqualTo("The ISBN format must be valid.");
    }
}
