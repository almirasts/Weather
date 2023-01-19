import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.InvokeMethodRunnable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AlmirastsTest {

    //    TC_1_1  - Тест кейс:
    //    //1. Открыть страницу https://openweathermap.org/
    //    //2. Набрать в строке поиска город Paris
    //    //3. Нажать пункт меню Search
    //    //4. Из выпадающего списка выбрать Paris, FR
    //    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceDropDownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        Thread.sleep(2000);
        parisFRChoiceDropDownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
// и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
    public void testGuideUrlAndHeader() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url);

        WebElement guideElementInMenu = driver.findElement(
                By.xpath("//body//nav[@id = 'nav-website']//ul[@id = 'first-level-nav']//div//ul//li//a[@href='/guide']")
        );
        Thread.sleep(5000);
        guideElementInMenu.click();
        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        driver.quit();
    }

    @Test
//    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Подтвердить, что температура для города показана в Фарингейтах
    public void testThatTempShowsInF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String fTemptSymbol = "°F";

        driver.get(url);
        driver.manage().window().maximize(); //эта команда помогает открыть окно на полный экран
        Thread.sleep(5000);

        WebElement menuImperial = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[@class='option']/following-sibling::div")
        );

        menuImperial.click();

        WebElement tempF = driver.findElement(
                By.xpath("//div[@class='current-temp']/span")
        );

        String tempInF = tempF.getText();

        String actualResult = tempInF.substring((tempInF.length() - 2));//метод Стринг, кот выводит
        // кусочек желаемого текста -20°F 85°F 120°F

        //      Assert.assertTrue(tempF.getText().contains(fTemptSymbol));
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    @Test
//   TC_11_03
//1. Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
// We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow
// all cookies or manage them individually.”
//3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    public void testConfirmTwoButtonsInPanel() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential "
                + "cookies to help us improve our services. Any data collected is "
                + "anonymised. You can allow all cookies or manage them individually.";

        String button1Text = "Allow all";
        String button2Text = "Manage cookies";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement textElement = driver.findElement(
                By.xpath("//p[@class='stick-footer-panel__description']")
        );
        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//button[text()='Allow all']")
        );
        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@href='/cookies-settings']")
        );

        Assert.assertEquals(buttonAllowAll.getText(), button1Text);
        Assert.assertEquals(buttonManageCookies.getText(), button2Text);
        Assert.assertEquals(textElement.getText(), expectedResult);

        driver.quit();
    }

    @Test
//    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
    public void testSupportSubMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String firstSubMenu = "FAQ";
        String secondSubMenu = "How to start";
        String thirdSubMenu = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement support = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );

        support.click();

        WebElement first = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li[@class='with-dropdown']/ul/li/a[@href='/faq']")
        );
        WebElement second = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li[@class='with-dropdown']/ul/li/a[@href='/appid']")
        );
        WebElement third = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li[@class='with-dropdown']/ul/li/a[text()='Ask a "
                        + "question']")
        );

        Assert.assertEquals(driver.findElements(
                By.xpath("//div[@id='desktop-menu']/ul/li[@class='with-dropdown']/ul/*"))
                .size(), 3
        );

        Assert.assertEquals(first.getText(), firstSubMenu);
        Assert.assertEquals(second.getText(), secondSubMenu);
        Assert.assertEquals(third.getText(), thirdSubMenu);

        driver.quit();
    }

    @Test
//   TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
    public void testVerifyCaptchaError() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement support = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );

        support.click();

        WebElement askAQuestionSubMenu = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li[@class='with-dropdown']/ul/li/a[text()='Ask a "
                        + "question']")
        );

        askAQuestionSubMenu.click();

        // Эти 2 линии используются, когда нужно переключиться на другую вкладку
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        WebElement emailField = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/input[@id='question_form_email']")
        );

        emailField.click();
        emailField.sendKeys("test@gmail.com");

        WebElement subjectField = driver.findElement(
                By.xpath("//select[@class='form-control select required']")
        );

        subjectField.click();

        WebElement anySubject = driver.findElement(
                By.xpath("//select[@class='form-control select required']/option[2]")
        );

        anySubject.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class='form-control text required']")
        );

        messageField.click();
        messageField.sendKeys("Test 123 @#$");

        WebElement submitButton = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/input[@type='submit']")
        );
        submitButton.click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='reCAPTCHA verification failed, "
                + "please try again.']")).getText(), expectedResult);


        driver.quit();
    }

    @Test
//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
    public void testEmailCantBeBlank() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult = "can't be blank";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement support = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );

        support.click();

        WebElement askAQuestionSubMenu = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li[@class='with-dropdown']/ul/li/a[text()='Ask a "
                        + "question']")
        );

        askAQuestionSubMenu.click();

        // Эти 2 линии используются, когда нужно переключиться на другую вкладку
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        WebElement subjectField = driver.findElement(
                By.xpath("//select[@class='form-control select required']")
        );

        subjectField.click();

        WebElement anySubject = driver.findElement(
                By.xpath("//select[@class='form-control select required']/option[2]")
        );

        anySubject.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class='form-control text required']")
        );

        messageField.click();
        messageField.clear();
        messageField.sendKeys("Test 123 @#$");

        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement captchaCheckBox = driver.findElement(
                By.xpath("//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
                        + "rc-anchor-checkbox']")
        );

        captchaCheckBox.click();
        Thread.sleep(7000);

        driver.switchTo().window(window2);

        WebElement submitButton = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/input[@type='submit']")
        );
        submitButton.click();

        Assert.assertEquals(driver.findElement(
                By.xpath("//span[@class='help-block']")).getText(), expectedResult
        );

        driver.quit();
    }

    @Test
//    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
    public void testVerifySwitchFromFtoC() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String tempValue = "°C";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement changingTempToF = driver.findElement(
                By.xpath("//div[@class='option'][contains(text(),'°F')]")
        );
        changingTempToF.click();
        Thread.sleep(2000);

        WebElement changingTempToC = driver.findElement(
                By.xpath("//div[@class='option'][contains(text(),'°C')]")
        );
        changingTempToC.click();
        Thread.sleep(2000);

        String tempC = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(),'°C')]")
        ).getText();
        Boolean actualResult = tempC.contains(tempValue);

        Assert.assertTrue(actualResult);


        driver.quit();
    }

    @Test
//    TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
    public void testVerifyCurrentLink() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult = url;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement logo = driver.findElement(
                By.xpath("//nav/ul/li/a[@href='/']/img")
        );
        logo.click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(), expectedResult);

        driver.quit();
    }

    @Test
//   TC_11_09
//1. Открыть базовую ссылку
//2. В строке поиска в навигационной панели набрать “Rome”
//3. Нажать клавишу Enter
//4. Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
    public void testSearchFieldContainsRome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult = "Rome";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/form/input[@placeholder='Weather in your city']")
        );

        searchCityField.click();
        searchCityField.sendKeys("Rome");
        searchCityField.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl().contains("find"), true);
        Assert.assertEquals(driver.getCurrentUrl().contains("Rome"), true);

        WebElement secondSearchField = driver.findElement(
                By.xpath("//input[@id='search_str']")
        );

        Assert.assertEquals(secondSearchField.getAttribute("value"), "Rome");

        driver.quit();
    }

    @Test
//    TC_11_10
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню API
//3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
    public void testApiButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement apiMenu = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/api']")
        );

        apiMenu.click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElements(
                By.xpath("//a[contains(@class, 'orange')]")
        ).size(), expectedResult);

        driver.quit();
    }




//    @Test
//    public void testH2TagText_WhenSearchingCityCountry() {
//        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
//        WebDriver driver = new ChromeDriver();
//
//
//        driver.quit();
//    }

}