package StepDefinitions;

import DatabaseData.DatabaseData;
import Functions.moneyFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Steps {

    WebDriver driver;


    //Variables
    String mainBankingSite = "https://sanbank.pl/klienci-indywidualni/internet/ibank-demo";
    //var loginInput = "id=\"j_username\"";
    String cookiesAcceptBtn = "/html/body/div[2]/div[2]/button";
    String demoBankingSite = "/html/body/div[1]/div[7]/div/div/div/div/p[3]/a[2]";
    String loginButton = "//*[@id=\"j_login\"]";


    //Give Credentials
    String loginInputElement = "//*[@id=\"j_username\"]";
    String userLogin = "demo2";
    String userPassword = "123456";

    //User Menu panel (top right)
    String btnLogout = "//*[@id=\"j_idt89:wylogujButton\"]";
    String DisplayedUsernameFullName = "/html/body/div[2]/div[1]/div[3]/div/ul/li[2]/p/b";
    String DisplayedUserShortName = "/html/body/div[2]/div[1]/div[3]/div/ul/li[3]/p/b";

    // New Elixir
    String randomGeneratedNRB = "31919700048273578465953657";
    String randomFullRecipientsName = "Dane losowe odbiorcy Firma123";
    String randomStreetName = "Testowa 17";
    String randomTransferAmount = "1000";
    String randomTransferTitle = "Transfer środków - faktura nr:123456789";

    ////////////////////////////////


    @Given("Test attributes")
    public void Test_attributes() {
        //setting the driver executable
        System.setProperty("webdriver.chrome.driver", "E:\\Pobrane\\chromedriver_win64\\chromedriver.exe");
        driver = new ChromeDriver();
        //Initiating your chromedriver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //maximize window
        driver.manage().window().maximize();

    }

    @When("Enter the main bank site")
    public void Enter_the_main_bank_site() {
        driver.get(mainBankingSite);
        driver.findElement(By.xpath(cookiesAcceptBtn)).click();

    }

    @And("Navigate to demo site")
    public void Navigate_to_demo_site() {
        var linkToDemo = driver.findElement(By.xpath(demoBankingSite));
        linkToDemo.click();
    }

    @Then("Gives credentials")
    public void Gives_credentials() {
        var loginInput = driver.findElement(By.xpath(loginInputElement));
        loginInput.click();
        System.out.println(driver.getTitle());
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable((loginInput)));
        loginInput.click();
        loginInput.sendKeys(userLogin + Keys.TAB + userPassword);
        driver.findElement(By.xpath(loginButton)).click();

    }

    @Then("Check if logged in")
    public void Check_if_logged_in() {
        System.out.println("Logged in!");
        String ExpectedTitleOfBankingDemoMainPage = "I-BANK SYSTEM DEMONSTRACYJNY - Start";
        String FetchedTitle = driver.getTitle();
        Assert.assertEquals(ExpectedTitleOfBankingDemoMainPage, FetchedTitle);
    }
    @Then("User logout")
    public void User_logout(){
        driver.findElement(By.xpath(btnLogout)).click();
    }

    @Then("Finish test")
    public void Finish_test(){
        System.out.println("Wywołanie końca testu - zamknięcie przeglądarki.");
        driver.quit();
    }

    DatabaseData databaseData = new DatabaseData();
    @And("Verify user data")
    public void Verify_user_data(){
        var menuBarUsersShortName = driver.findElement(By.xpath(DisplayedUserShortName));
        String UsersShortName = menuBarUsersShortName.getText();
        Assert.assertEquals(UsersShortName, databaseData.UserShortName);
        var menuBarUsersFullName = driver.findElement(By.xpath(DisplayedUsernameFullName));
        String UsersFullName = menuBarUsersFullName.getText();
        Assert.assertEquals(UsersFullName, databaseData.UserFullName);
    }
    @And("Verify balance amount")
    public void Verify_balance_amount(){
        String databaseAccountOneBalance = Double.toString(databaseData.accountBalance09836700000010101200000003);
        String databaseAccountOneCurrency = databaseData.accountBalance09836700000010101200000003Currency;
        var accountOneBalance = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[3]/div/div/div[1]/table/tbody/tr[2]/td[2]/span"));
        String accountOneBalanceVerify = accountOneBalance.getText();
        String balanceOneAsString = databaseAccountOneBalance;
        moneyFunctions mF = new moneyFunctions();
        String testResultFetchedFromMoneyConv = mF.MoneyConverter(balanceOneAsString,databaseAccountOneCurrency );
        Assert.assertEquals(accountOneBalanceVerify, testResultFetchedFromMoneyConv);

    }
    String newElixirUrl = "https://demo.sabaservice.pl/hb/faces/web/dyspozycje/zlecenia/przelewy/dodawanie-przelewow-elixir.html";
    String MenuNewTransfer = "/html/body/div[2]/div[2]/div/div[1]/div[1]/div[2]/form/div/div[3]";
    @When("Navigate to new elixir transfer site")
    public void Navigate_to_new_elixir_transfer_site(){
        driver.findElement(By.xpath(MenuNewTransfer)).click();
        driver.findElement (By.xpath ("//*[contains(text(),'Przelew krajowy')]")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, newElixirUrl);
    }
    @And("New elixir page is displayed properly")
    public void New_elixir_page_is_displayed_properly(){
        //Optional step

    }
    @Then("Fill new elixir transfer data")
    public void Fill_new_elixir_transfer_data(){
       var NewRecipientsNRBInput = driver.findElement(By.id("ibankForm:elixirPanel:numerRachunkuField"));
       NewRecipientsNRBInput.click();
       NewRecipientsNRBInput.sendKeys(randomGeneratedNRB);
       var NewFullRecipientsName = driver.findElement(By.id("ibankForm:elixirPanel:nazwaAdresataPelnaField1"));
       NewFullRecipientsName.click();
       NewFullRecipientsName.sendKeys(randomFullRecipientsName);
       var NewStreetName = driver.findElement(By.id("ibankForm:elixirPanel:adresAdresataField"));
       NewStreetName.click();
       NewStreetName.sendKeys(randomStreetName);
       var NewTransferAmount = driver.findElement(By.xpath("//*[@id=\"ibankForm:elixirPanel:kwotaField\"]"));
       NewTransferAmount.click();
       NewTransferAmount.sendKeys(randomTransferAmount);
       var NewTransferTitle = driver.findElement(By.id("ibankForm:elixirPanel:tytulField1"));
       NewTransferTitle.click();
       NewTransferTitle.sendKeys(randomTransferTitle);
       driver.findElement(By.id("ibankForm:elixirPanel:zatwierdzButton")).click();
        System.out.println("Oba rachunki nie mają środków na koncie!");

    }
}

