package me.myshows;

import com.codeborne.selenide.Selenide;
import me.myshows.domain.MenuItem;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class SimpleTest {

    @ValueSource (strings = {
            "Шерлок",
            "Аватар"
    })

    @ParameterizedTest(name = "Проверка поиска на сайте myShows по слову {0}")
    void myShowsTest(String testData) {
        Selenide.open("https://myshows.me/");
        $(".Search-input").setValue(testData);
        $("button[type='submit']").click();
        $$(".Row-container")
                .find(text(testData))
                .shouldBe(visible);

    }

    @CsvSource(value = {
            "Шерлок | Miss Sherlock",
            "Аватар | The King's Avatar"
    },
            delimiter = '|'
    )
    @ParameterizedTest(name = "Проверка поиска на сайте myShows по слову {0}, ожидаем результат {1}")
    void myShowsComplexTest(String testData, String expectedResult) {
        Selenide.open("https://myshows.me/");
        $(".Search-input").setValue(testData);
        $("button[type='submit']").click();
        $$(".Row-container")
                .find(text(expectedResult))
                .shouldBe(visible);

    }

    static Stream<Arguments> myShowsSourceTest() {
        return Stream.of(
                Arguments.of("Шерлок","Мисс Шерлок")
        );
    }

    @MethodSource("myShowsSourceTest")
    @ParameterizedTest(name = "Проверка поиска на сайте myShows по слову {0}, ожидаем результат {1}")
    void myShowsSourceTest(String first, String second) {
        Selenide.open("https://myshows.me/");
        $(".Search-input").setValue(first);
        $("button[type='submit']").click();
        $$(".Row-container")
                .find(text(second))
                .shouldBe(visible);
    }
    @EnumSource(MenuItem.class)
    @ParameterizedTest(name = "Проверка поиска на сайте myShows по слову {0}")
    void myShowsMenuTest(MenuItem testData) {
        Selenide.open("https://myshows.me/");
        $(".Search-input").setValue("Ведьмак");
        $("button[type='submit']").click();
        $$(".Container")
                .find(text(testData.rusName))
                .click();
    }

}