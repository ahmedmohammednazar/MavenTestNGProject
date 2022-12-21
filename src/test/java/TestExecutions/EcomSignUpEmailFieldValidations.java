package TestExecutions;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.EcommerceCommonPage;
import pages.EcommerceSignUpPage;
import utils.Driver;
import utils.ExcelUtils;
import utils.TestDataReader;

public class EcomSignUpEmailFieldValidations {
	@BeforeMethod
	public void setUp() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
	}
	
	@Test 
	public void test1() {
		
		ExcelUtils.openExcelFile("./src/test/resources/testData/testDataExcelEcomm.xlsx", "Test Data");
		System.out.println("Total row count : " + ExcelUtils.getUsedRowsCount());
		System.out.println(ExcelUtils.getCellData(6, 0));
	}
  @Test(dataProvider = "emailEntry")
  public void signUpEmailValidation(String item) {
	  Driver.getDriver().get(TestDataReader.getProperty("EcommerceUrl"));
	  EcommerceCommonPage commonPage = new EcommerceCommonPage();
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  
	 EcommerceSignUpPage ecommercePage = new EcommerceSignUpPage();
	 ecommercePage.emailField.sendKeys(item);
	 ecommercePage.signUpBtn.click();
	 

Assert.assertEquals(ecommercePage.emailErorrMesg.getText(), "The email may not be greater than 125 characters.");

Assert.assertEquals(ecommercePage.emailErorrMesg.getText(), "Please enter a valid email address.");





	
	  
  }
  
  @DataProvider
  
  public String[] emailEntry() {
	  
	  String []items = ExcelUtils.getExcelDataInAColumn("./src/test/resources/testData/testDataExcelEcomm.xlsx", "Test Data");
	  return items;
  }
  
  
}
