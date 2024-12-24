import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterSteps {
    private final WebDriver driver;

    public FooterSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу")
    public void openPage(String url) {
        driver.get(url);
    }

    @Step("Страница загружена")
    public void stepPageLoading() {
        System.out.println("Ожидаем загрузку страницы...");
    }

    @Step("Ошибка: страница не загрузилась.")
    public void stepPageNotLoading() {
        System.out.println("Логотип не появился на экране. Возможно, сайт не загрузился корректно.");
    }

    @Step("Индикатор загрузки страницы найден")
    public void stepIndicatorFound() {
        System.out.println("Индикатор загрузки страницы найден");
    }

    @Step("Индикатор загрузки страницы не найден")
    public void stepIndicatorNotFound() {
        System.out.println("Индикатор загрузки страницы не найден.");
    }

    @Step("Кликнули по телу страницы для фокуса.")
    public void stepFocusClicked() {
        System.out.println("Клик выполнен.");
    }

    @Step("Футер в коде страницы найден. Проверяем видимость.")
    public void stepFooterFound(String url) {
        System.out.println("На странице " + url + " найден футер. Проверяем, виден ли он пользователю...");
    }

    @Step("Ошибка при прокрутке страницы: {message}")
    public void stepScrollInterrupted(String message) {
        System.out.println("Ошибка при ожидании завершения прокрутки: " + message);
    }

    @Step("Футер в коде страницы отсутствует.")
    public void stepFooterAbsent(String url) {
        System.out.println("Футер в коде страницы " + url + " отсутствует.");
    }

    @Step("Футер на странице ЕСТЬ И ВИДЕН пользователю.")
    public void stepFooterVisible(String url) {
        System.out.println("Футер на странице " + url + " есть и виден пользователю.");
    }

    @Step("Футер на странице ЕСТЬ, но НЕ ВИДЕН пользователю.")
    public void stepFooterNotVisible(String url) {
        System.out.println("Футер на странице " + url + " есть, но не виден пользователю.");
    }

    @Step("Элемент {elementName}: {status}")
    public void stepElementStatus(String elementName, String status, WebElement element) {
        System.out.println("Элемент '" + elementName + "' имеет статус: " + status);

        // Проверяем, если элемент существует, является ли ссылкой и имеет ли атрибут href
        if (element != null && "a".equalsIgnoreCase(element.getTagName()) && element.getAttribute("href") != null) {
            String href = element.getAttribute("href");
            logLinkHref(elementName, href);
        }
    }

    @Step("Ссылка: {href}")
    public void logLinkHref(String elementName, String href) {
        System.out.println("У элемента '" + elementName + "' найдена ссылка: " + href);
    }
}