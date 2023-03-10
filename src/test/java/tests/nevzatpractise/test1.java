package tests.nevzatpractise;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ieEnstitues.*;
import utilities.ConfigReader;
import utilities.Driver;

public class test1 {
    // Kullanici https://www.iienstitu.com/en sayfasina gider
    // Login butonuna tiklar
    // Giris bilgilerini girer ve basarili sekilde giris yaptigini test eder
    // headers da bulunan egitimler kismina tiklar
    // acilan egitimler sayfasindan fiyat araligi olarak artan fiyat olarak secer
    // ucretsiz egitimlerden herhangi birini alir
    // kimlik dogrulama islemlerini girer
    // egitimin ucretsiz oldugunu test eder ve siparisi tamamlar.



    UdemyHomePage homepage=new UdemyHomePage();
    LoginPage loginPage=new LoginPage();
    MyPage myPage=new MyPage();
    OnlineEgitimlerPage online=new OnlineEgitimlerPage();
    KimlikDogrulama kimlik=new KimlikDogrulama();
    @Test
    public void test01(){
        Driver.getDriver().get("https://www.iienstitu.com/en");
        // Login butonuna tiklar
        homepage.loginButtonHomePage.click();
        // Giris bilgilerini girer ve basarili sekilde giris yaptigini test eder
        loginPage.loginEmailBox.sendKeys(ConfigReader.getProperty("email"));
        loginPage.loginPasswordBox.sendKeys(ConfigReader.getProperty("password"));
        loginPage.submitButton.click();
        Assert.assertTrue(myPage.loggedCheckControl.isDisplayed());

        // headers da bulunan egitimler kismina tiklar
        myPage.egitimlerOnHeaders.click();
        // acilan egitimler sayfasindan fiyat araligi olarak artan fiyat olarak secer
        Select select=new Select(online.onlineSiralamaDdm);
        select.selectByValue("price_ASC");
        online.lessonStresYonetimiEgitimi.click();
        online.hemenBaslaButton.click();
        // kimlik dogrulama islemlerini girer
        Actions actions=new Actions(Driver.getDriver());
        actions.sendKeys(kimlik.kimlikDogrulamMail,ConfigReader.getProperty("email")).
                sendKeys(Keys.TAB).
                sendKeys(Keys.TAB).
                sendKeys(Keys.TAB).
                sendKeys("nevzat").
                sendKeys(Keys.TAB).
                sendKeys("celik").
                sendKeys(Keys.TAB).
                sendKeys("varsova").
                sendKeys(Keys.TAB).
                sendKeys("15600").
                sendKeys(Keys.TAB).
                sendKeys("Varsova").
                sendKeys(Keys.TAB).
                sendKeys("11111111111").perform();
        kimlik.kimlikDogrulamaOdemeyeGec.click();
        // egitimin ucretsiz oldugunu test eder ve siparisi tamamlar
        Assert.assertTrue(kimlik.ucretsizEgitimCheck.isDisplayed());
        kimlik.siparisiTamamlaButton.click();
    }

    @Test
    public void stresYonetimiKontrol(){
        Driver.getDriver().get("https://www.iienstitu.com/en");
        homepage.loginButtonHomePage.click();
        loginPage.loginBilgiler();

    }

}

