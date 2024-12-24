import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FooterTest extends BaseTest {

    // Создаем объект для вызова шагов Allure
    private FooterSteps footerSteps;

    @Test
    @DisplayName("Проверка футера на страницах сайта")
    public void testFooterVisibility() {
        footerSteps = new FooterSteps(driver); // Передаём driver
        // Список URL для тестирования
        String[] urls = {
                TestData.BASE_URL,
                TestData.PROJECTS_URL,
                TestData.COMPANY_URL,
                TestData.CAREER_URL,
                TestData.CONTACTS_URL,
                TestData.QUESTIONNAIRE_URL
        };

        // Проверяем футер на каждой странице и элементы
        for (String url : urls) {
            testPage(url);
        }
    }

    @Step("Тестируем страницу: {url}")
    public void testPage (String url){
        openAndLoadPage(url);
        checkFooterOnPage(url);
        checkFooterElements();
    }

    @Step("Страница открыта, контент загружен")
    public void openAndLoadPage (String url){
        footerSteps.openPage(url); // Открываем страницу
        waitForPageToLoad(); // Ждём пока страница откроется
        waitForIndicatorAndClick(); // Ждем пока загрузится контент
    }

    @Step("Ожидание загрузки страницы...")
    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestData.LOGO_XPATH)));
            footerSteps.stepPageLoading();  // Индикатор загрузки страницы найден
        } catch (Exception e) {
            footerSteps.stepPageNotLoading();  // Индикатор загрузки страницы не найден
        }
    }

    @Step("Страница загрузилась")
    public void waitForIndicatorAndClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement cookiesMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestData.COOKIES_MASSAGE_XPATH)));
            footerSteps.stepIndicatorFound(); // Индикатор загрузки контента найден

            // Кликаем по телу сайта для фокусировки
            WebElement body = driver.findElement(By.tagName("body"));
            body.click();
            footerSteps.stepFocusClicked();
        } catch (Exception e) {
            footerSteps.stepIndicatorNotFound(); // Индикатор загрузки контента не найден
        }
    }

    @Step("Проверка наличия футера в коде страницы")
    public boolean isFooterPresent(String url) {
        try {
            WebElement footer = driver.findElement(By.xpath(TestData.FOOTER_XPATH));
            footerSteps.stepFooterFound(url); // Футер найден в коде страницы
            return true; // Футер найден
        } catch (NoSuchElementException e) {
            footerSteps.stepFooterAbsent(url); // Футер не найден в коде страницы
            return false; // Футер не найден
        }
    }

    @Step("Проверяем футер")
    public void checkFooterOnPage(String url) {
        if (isFooterPresent(url)) { // Если футер есть
            boolean footerVisible = scrollAndCheckFooterVisibility(); // Прокручиваем страницу и проверяем видимость
            if (footerVisible) {
                footerSteps.stepFooterVisible(url); // Футер найден и виден
            } else {
                footerSteps.stepFooterNotVisible(url); // Футер найден, но не виден
            }
        } else {
            footerSteps.stepFooterAbsent(url); // Футера в коде нет
        }
    }

    @Step("Футер найден в коде, но не виден. Скроллим страницу")
    private boolean scrollAndCheckFooterVisibility() {
        // Ожидание перед прокруткой
        TestHelper.waitForScroll(500);

        // Проверка видимости футера
        return TestHelper.scrollAndCheckVisibility(driver, TestData.FOOTER_XPATH, 100);
    }

    @Step("Проверяем элементы в футере")
    public void checkFooterElements() {
        // Проверяемые элементы
        String[][] footerElements = {
                {TestData.START_A_PROJECT_IN_FOOTER_XPATH, "Начать проект"},
                {TestData.PHONE_IN_FOOTER_XPATH, "Номер телефона"},
                {TestData.EMAIL_IN_FOOTER_XPATH, "Email"},
                {TestData.BUTTON_UP_IN_FOOTER_XPATH, "Кнопка наверх"},
                {TestData.LINK_AWWWARDS_IN_FOOTER_XPATH, "Ссылка на Awwwards"},
                {TestData.LINK_VK_IN_FOOTER_XPATH, "Ссылка на VK"},
                {TestData.LINK_TELEGRAM_IN_FOOTER_XPATH, "Ссылка на Telegram"},
                {TestData.LINK_VIMEO_IN_FOOTER_XPATH, "Ссылка на Vimeo"},
                {TestData.LINK_BEHANCE_IN_FOOTER_XPATH, "Ссылка на Behance"}
        };

        // Цикл по каждому элементу футера
        for (String[] element : footerElements) {
            checkElementPresence(element[0], element[1]);
        }
    }

    private void checkElementPresence(String xpath, String elementName) {
        // Получаем статус элемента
        String status = TestHelper.checkElementVisibility(driver, xpath);

        WebElement element = null;
        if (!status.equals("не найден в коде")) {
            element = driver.findElement(By.xpath(xpath));  // Находим элемент только если он существует
        }

        // Вызываем шаг отображения статуса в отчете
        footerSteps.stepElementStatus(elementName, status, element);
    }
}

