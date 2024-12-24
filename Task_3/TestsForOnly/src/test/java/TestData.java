/*
В этом классе будем хранить ссылки
 */

public class TestData {
    //Веб-драйвер
    public static final String DRIVER_PATH = "C:/WebDriver/bin/chromedriver.exe";

    // URL сайта
    public static final String BASE_URL = "https://only.digital/";
    // Проекты
    public static final String PROJECTS_URL = BASE_URL + "projects";
    // Компания
    public static final String COMPANY_URL = BASE_URL + "company";
    // Карьера
    public static final String CAREER_URL = BASE_URL + "job";
    // Контакты
    public static final String CONTACTS_URL = BASE_URL + "contacts";
    // Анкета
    public static final String QUESTIONNAIRE_URL = BASE_URL + "#brief";




    // Футер
    public static final String FOOTER_XPATH = ".//footer";
    // Начать проект
    public static final String START_A_PROJECT_IN_FOOTER_XPATH = ".//footer//p[text()=\"Начать проект\"]";
    // Номер телефона
    public static final String PHONE_IN_FOOTER_XPATH = ".//footer//a[starts-with(@href, \"tel:\")]";
    // Email
    public static final String EMAIL_IN_FOOTER_XPATH = ".//footer//a[starts-with(@href, \"mailto:\")]";
    // Кнопка наверх
    public static final String  BUTTON_UP_IN_FOOTER_XPATH = ".//footer//div[contains(@class, \"sc-222969c7-9\")]";
    // Ссылка на Awwwards
    public static final String  LINK_AWWWARDS_IN_FOOTER_XPATH = ".//footer//span[text()=\"Awwwards\"]/ancestor::a";
    // Ссылка на VK
    public static final String  LINK_VK_IN_FOOTER_XPATH = ".//footer//span[text()=\"Vkontakte\"]/ancestor::a";
    // Ссылка на Telegram
    public static final String  LINK_TELEGRAM_IN_FOOTER_XPATH = ".//footer//span[text()=\"Telegram\"]/ancestor::a";
    // Ссылка на Vimeo
    public static final String  LINK_VIMEO_IN_FOOTER_XPATH = ".//footer//span[text()=\"Vimeo\"]/ancestor::a";
    // Ссылка на Behance
    public static final String  LINK_BEHANCE_IN_FOOTER_XPATH = ".//footer//span[text()=\"Behance\"]/ancestor::a";


    // Логотип
    public static final String LOGO_XPATH = "//*[@id=\"Header\"]/a";

    // Сообщение про куки
    public static final String COOKIES_MASSAGE_XPATH = ".//p[text()=\"Сookies\"]";

}
