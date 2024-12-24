import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;


public class TestHelper {

    /**
     * Ожидания перед прокруткой, если контент долго грузится
     *
     * @param delayMillis       Время ожидание в мСек.
     */
    public static void waitForScroll(long delayMillis) {
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Ожидание было прервано: " + e.getMessage(), e);
        }
    }

    /**
     * Прокрутка страницы вниз и проверка видимости элемента по XPath.
     *
     * @param driver       WebDriver для взаимодействия с браузером.
     * @param xpath        XPath элемента, который нужно найти.
     * @param maxAttempts  Максимальное количество попыток прокрутки.
     * @return true, если элемент стал видимым, иначе false.
     */
    public static boolean scrollAndCheckVisibility(WebDriver driver, String xpath, int maxAttempts) {
        Actions actions = new Actions(driver);
        boolean isVisible = false;
        int attempts = 0;

        while (!isVisible && attempts < maxAttempts) {
            try {
                // Выполняем прокрутку
                actions.sendKeys(Keys.PAGE_DOWN).perform();
                Thread.sleep(5); // Пауза между прокрутками

                // Проверяем видимость элемента
                WebElement element = driver.findElement(By.xpath(xpath));
                if (element.isDisplayed()) {
                    isVisible = true; // Элемент виден
                    break;
                }
            } catch (NoSuchElementException e) {
                // Игнорируем отсутствие элемента
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Прерываем поток
                throw new RuntimeException("Ожидание прокрутки прервано: " + e.getMessage(), e);
            }
            attempts++;
        }
        return isVisible;
    }


    /**
     * Проверка наличия элемента и его видимости на странице.
     *
     * @param driver       WebDriver для взаимодействия с браузером.
     * @param xpath        XPath элемента для поиска.
     * @return Статус элемента.
     */
    public static String checkElementVisibility(WebDriver driver, String xpath) {
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath(xpath));  // Находим элемент
            if (element.isDisplayed()) {
                return "найден в коде и виден на странице";
            } else {
                return "найден в коде, но не виден на странице";
            }
        } catch (NoSuchElementException e) {
            return "не найден в коде";  // Если элемент не найден
        }
    }
}

