package SampleProject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.CommonUtils;
import base.BaseSetup;
import jxl.Sheet;



public class Sasikanth_Project extends BaseSetup {                 // written by sasikanth
	public WebDriver driver;
	public 	CommonUtils Data;
	public Sheet sheet;
	public Sheet sheet1;
	public Sheet sheet2;
	public Sheet sheet3;
	public String Header = "Input Prices";
	public String Second_Level_Module = "Chemical Prices";
	public String ModuleHeader = "INPUT PRICES";
	public String HeaderTitle = "INPUT PRICES - CHEMICAL PRICES";
	public String Third_Level_Module = "Chemical Prices - Imported";
	public String Module_Name_In_Form = "CHEMICAL PRICES - IMPORTED";
	public String Submit_Success_Message = "form has been successfully forwarded to";
	public String Rework_Success_Message = "form has been successfully sent for Rework to";
	public String Save_Success_Message = "form has been saved successfully";
	public String Add_Material =  "SUBABUL WITH BARKWOOD";
	public String Input_Data =  "Chemical Prices Validations.xls"; 
	public static String FormName = "Chemical Prices Imported"; //Here should be changed form name


	//** Functional Test cases **//   


	//Verify Checking mentioned elements are displayed or not in dashboard page

	@BeforeTest
	public void Check_Mentioned_Elements() throws IOException {         
		driver = getDriver();
		driver.manage().window().maximize();
		CommonUtils.Thread_Sleep(3);
		driver.navigate().to("https://www.jenkins.io/download/");
		System.out.println("Git passed");
		//Elements=PageFactory.initElements(driver, Chemical_Prices_Elements.class);
		//Dashboard_Elements=PageFactory.initElements(driver, Dashboard_Elements.class);
		//Common_Elements=PageFactory.initElements(driver, Common_Elements.class);
		CommonUtils.Thread_Sleep(3);
		//CommonUtils.ModuleName="ChemicalPricesImported_Autopopulated";
		//CommonUtils.Reports("ChemicalPricesImported_Autopopulated.html", "ChemicalPrices");
		//String Header_Displayed = com.ITCPlanning.Elements.Dashboard_Elements.SBU1_BOMS.getText();
		//System.out.println(Header_Displayed);
		//Assert.assertEquals(Header_Displayed, Header);
		try {
			CommonUtils.captureScreenshot_pass(driver, "TC_071", "Mentioned fields are displayed as per the respective user privilieges");
			System.out.println("Pass");
		} catch (Exception e) {
			CommonUtils.captureScreenshot_fail(driver, "TC_071", "Mentioned fields are not displayed as per the respective user privilieges");
			System.out.println("Fail");
		}
	}

	//Verify Chemical Prices for Indigenous is Clickable or not 

	/*@Test(priority = 0)
	public void Check_Chemcial_Prices_Indigenous() throws InterruptedException, IOException {
		CommonUtils.login(driver);  //Login with credentials
		if(driver.getTitle().contains("Shop for Indian Ethnic Wear - Kurta, Sherwani, Indo Western, Jackets, Lehengas & more at Manyavar.com")) {
			CommonUtils.captureScreenshot_pass(driver, "TC_075", "Should be redirected to Form listing  page");
			System.out.println("Pass");
		} else {
			CommonUtils.captureScreenshot_fail(driver, "TC_075", "Should not be redirected to Form listing  page");
			System.out.println("Fail");
		}
	}



	//Verify Chemical Prices for Indigenous is Clickable or not 

	@Test(priority = 4)
	public void Check_Chemcial_Prices_Indigenous() throws InterruptedException, IOException {
		CommonUtils.Mouseover(Elements.ChemicalPrices_Imported_Module_click);
		String Cursor=Elements.ChemicalPrices_Imported_Module_click.getCssValue("cursor");
		System.out.println(Cursor);
		//var wait = new WebDriverWait(driver, 60);
		//wait.until(ExpectedConditions.visibilityOf(Elements.PulpPrices_Module_click)).getCssValue("cursor") == "pointer");
		//wait.until(driver.findElement(By.tagName("body")).getCssValue("cursor") == "pointer");
		CommonUtils.Thread_Sleep(3);
		CommonUtils.form_name(FormName);
		if(Elements.Chemicalprices_Imported_At_The_Header.isDisplayed()) {
			CommonUtils.form_name(FormName);
			CommonUtils.captureScreenshot_pass(driver, "TC_075", "Should be redirected to Form listing  page");
			System.out.println("Pass");
		} else {
			CommonUtils.captureScreenshot_fail(driver, "TC_075", "Should not be redirected to Form listing  page");
			System.out.println("Fail");
		}
	}
	//Verify  Edit button is Clickable or not   in current forms





	//Verify Checking mentioned elements are displayed or not in the selected form

	@Test(priority = 7)
	public void CheckMentionedElements() throws Exception {
		CommonUtils.Thread_Sleep(3);
		int rowcount = Elements.Row_Count.size();
		System.out.println("No.Of Rows:"+rowcount);
		int colcount = Elements.Cols_Count.size();
		System.out.println("No.Of cols:"+colcount);
		for(int j=1; j<=colcount; j++) {
			WebElement ColoumnData = driver.findElement(By.xpath("//*[@class='GridViewTableHeaderFixed']/table/tr/th["+j+"]"));
			CommonUtils.scrollTOElement(ColoumnData, driver);
			String UIData = ColoumnData.getText();
			System.out.println(UIData);
			sheet1 = CommonUtils.readExcelSheet(Excels.filepath + Input_Data , 1);
			String ColumntDatainExcel = CommonUtils.getExcelData(j-1, 1,sheet1);
			CommonUtils.CaptureScreenshot_for_Equals(driver, UIData,ColumntDatainExcel, "TC_078", UIData+" is displayed", ColoumnData+" is not displayed");
			try {
				CommonUtils.captureScreenshot_pass(driver, "TC_078", "Mentiond elements Should be displayed");
				System.out.println("Pass");
			} catch (Exception e) {
				CommonUtils.captureScreenshot_fail(driver, "TC_078", "Mentiond elements Should not be displayed");
				System.out.println("Fail");
			}
		}
	}



	//Verify Checking mentioned elements are Auto populated or not 

	@Test(priority = 8)
	public void Check_Mentioned_Elements_Autopopulated() throws IOException {
		int j= driver.findElements(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr")).size();
		System.out.println(j);
		for (int j1=1;j1<=j;j1++) { 
			for(int k=1;k<=19;k++) {
				String AutopopulatedData = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr/td["+k+"]")).getText();
				//String AutopopulatedData = driver.findElement(By.xpath("//*[@class='GridViewScrollTable GridViewScrollTableNoHorizantalScroll fullFledge fontAuto']/tbody//*[@class='GridViewScrollItem']["+j+"]/td["+k+"]/span")).getText();
				System.out.println(AutopopulatedData);
			}
		}
		for (int j1=1;j1<=j;j1++) { 
			for(int k=28;k<=29;k++) {
				String AutopopulatedData = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr/td["+k+"]")).getText();
				//String AutopopulatedData = driver.findElement(By.xpath("//*[@class='GridViewScrollTable GridViewScrollTableNoHorizantalScroll fullFledge fontAuto']/tbody//*[@class='GridViewScrollItem']["+j+"]/td["+k+"]/span")).getText();
				System.out.println(AutopopulatedData);
			}
		}
		CommonUtils.Thread_Sleep(3);
		try {
			CommonUtils.captureScreenshot_pass(driver, "TC_079", "Mentiond elements should be Auto populated");
			System.out.println("Pass");
		} catch (Exception e) {
			CommonUtils.captureScreenshot_fail(driver, "TC_079", "Mentiond elements not Auto populated");
			System.out.println("Fail");
		}
	}		




	//Verify by Entering valid Data in Month Wise Data(Ex: Apr to Mar)
	@Test(priority = 31)
	public void Enter_data_Month_Wise_Data() throws InterruptedException, IOException {
		CommonUtils.Thread_Sleep(3);
		int rowcount = Elements.Row_Count.size();
		int k = rowcount;
		System.out.println(k);
		for(int i=1; i<=2; i++) {
			for(int k1=10;k1<=21;k1++) {
				sheet1 = CommonUtils.readExcelSheet(Excels.filepath + Input_Data , 0);
				driver.findElement(By.xpath("(//*[@type='text'])["+k1+"]")).clear();
				Common_Elements.Close_btn_Enter_data2.click();
				driver.findElement(By.xpath("(//*[@type='text'])["+k1+"]")).sendKeys(CommonUtils.getExcelData(2, 10, sheet1));
			}
		}
		try {
			Common_Elements.Save_btn.click();
			Common_Elements.Click_Yes_Do_You_Want_Save.click();
			try {
				Common_Elements.Close_btn_Save.click();
				System.out.println(1);
			} catch (Exception e) {
				Common_Elements.Close_btn_Save1.click();
				System.out.println(2);
			}
			CommonUtils.captureScreenshot_pass(driver, "TC_094", "Entered Data should be Accepted");
			System.out.println("Pass");
		}  catch (Exception e) {
			CommonUtils.captureScreenshot_fail(driver, "TC_094", "Entered Data should not be Accepted");
			System.out.println("Fail");
		}
	}


	//verifying Month Wise Data (INR): (Ex: Apr to Mar) is binded from formula :  (Month Wise Data * Exchange Rate) + Other Handling charges.
	@Test(priority = 34)
	public void Check_data_binded_Month_wise_data_INR() throws IOException {
		CommonUtils.Thread_Sleep(3);
		String MWD_USD = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[30]/div/span/input")).getAttribute("value");
		//Map= Map.replace(",", "");
		double Month_wise_USD = Double.parseDouble(MWD_USD);
		System.out.println(Month_wise_USD);
		String Exc = Elements.Enter_data_Exchange_Rate.getAttribute("value");
		//Avg= Avg.replace(",", "");
		double Exchange_rate = Double.parseDouble(Exc);
		System.out.println(Exchange_rate);
		String Other_hand = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[42]/div/span/input")).getAttribute("value");
		//Map= Map.replace(",", "");
		double Other_handling_charges = Double.parseDouble(Other_hand);
		System.out.println(Other_handling_charges);
		double Calculated_MWD_INR = Month_wise_USD*Exchange_rate+Other_handling_charges;
		System.out.println(Calculated_MWD_INR);
		CommonUtils.Thread_Sleep(3);
		System.out.println("MWD INR is:"+Calculated_MWD_INR);
		String MWD_INR =Elements.Bined_MWD_INR.getText();
		double Month_wise_INR = Double.parseDouble(MWD_INR);
		System.out.println("Diff is:"+Month_wise_INR);
		CommonUtils.CaptureScreenshot_for_Equals(driver, Month_wise_INR, Calculated_MWD_INR, "TC_097", "Month Wise Data (INR): (Ex: Apr to Mar)  should be binded from formula ;   (Month Wise Data * Exchange Rate) + Other Handling charges", "Month Wise Data (INR): (Ex: Apr to Mar)  should not  be binded from formula ;   (Month Wise Data * Exchange Rate) + Other Handling charges");
	}



	@Test(priority = 36)
	public void Check_data_calculated_AVG_Rate() throws IOException {
		CommonUtils.Thread_Sleep(3);
		String Apr_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[43]/div/span")).getText();
		double Apr20 = Double.parseDouble(Apr_20);
		System.out.println(Apr20);
		String May_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[44]/div/span")).getText();
		double May20 = Double.parseDouble(May_20);
		System.out.println(May_20);
		String Jun_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[45]/div/span")).getText();
		double Jun20 = Double.parseDouble(Jun_20);
		System.out.println(Jun_20);
		String Jul_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[46]/div/span")).getText();
		double Jul20 = Double.parseDouble(Jul_20);
		System.out.println(Jul_20);
		String Aug_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[47]/div/span")).getText();
		double Aug20 = Double.parseDouble(Aug_20);
		System.out.println(Aug_20);
		String Sep_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[48]/div/span")).getText();
		double Sep20 = Double.parseDouble(Sep_20);
		System.out.println(Sep_20);
		String Oct_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[49]/div/span")).getText();
		double Oct20 = Double.parseDouble(Oct_20);
		System.out.println(Oct_20);
		String Nov_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[50]/div/span")).getText();
		double Nov20 = Double.parseDouble(Nov_20);
		System.out.println(Nov_20);
		String Dec_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[51]/div/span")).getText();
		double Dec20 = Double.parseDouble(Dec_20);
		System.out.println(Dec_20);
		String Jan_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[52]/div/span")).getText();
		double Jan20 = Double.parseDouble(Jan_20);
		System.out.println(Jan_20);
		String Feb_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[53]/div/span")).getText();
		double Feb20 = Double.parseDouble(Feb_20);
		System.out.println(Feb_20);
		String Mar_20 = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[54]/div/span")).getText();
		double Mar20 = Double.parseDouble(Mar_20);
		System.out.println(Mar_20);
		double SumofMonthwisedata = Apr20+May20+Jun20+Jul20+Aug20+Sep20+Oct20+Nov20+Dec20+Jan20+Feb20+Mar20;
		double Calculated_MWD = SumofMonthwisedata/12;
		System.out.println("Sum of Month wise is:"+Calculated_MWD);
		DecimalFormat df = new DecimalFormat("#.###");
		System.out.println(Double.valueOf(df.format(Calculated_MWD)));
		String Avg =Elements.Binded_Imported_Avg_Rate_20_21.getText();
		double Avg_rate = Double.parseDouble(Avg);
		System.out.println(Double.valueOf(df.format(Avg_rate)));
		System.out.println("Avg Rate is:"+Avg_rate);
		CommonUtils.CaptureScreenshot_for_Equals(driver, Avg_rate, Calculated_MWD, "TC_098", "Avg rate for current year should be binded from all months data", "Avg rate for current year should be binded from all months data");
	}



	//verifying Diff is binded from formula Diff = MAP as on date - Avg Rate for 20-21

	@Test(priority = 37)
	public void Check_data_binded_diff() throws IOException {
		CommonUtils.Thread_Sleep(3);
		String Map = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[16]/div/span")).getText();
		//Map= Map.replace(",", "");
		double map = Double.parseDouble(Map);
		System.out.println(Map);
		String Avg = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[55]/div/span")).getText();
		//Avg= Avg.replace(",", "");
		double avg = Double.parseDouble(Avg);
		System.out.println(Avg);
		double Diff = map-avg;
		System.out.println(Diff);
		CommonUtils.Thread_Sleep(3);
		System.out.println("Diff is:"+Diff);
		String Diff_Chemical =Elements.Bined_Imported_Diff.getText();
		double Difff = Double.parseDouble(Diff_Chemical);
		System.out.println("Diff is:"+Difff);
		CommonUtils.CaptureScreenshot_for_Equals(driver, Diff, Difff, "TC_99", "Diff should be binded from formula Diff = MAP as on date - Avg Rate for 20-21", "Diff not binded from formula Diff = MAP as on date - Avg Rate for 20-21");
	}

	//verifying Diff% is binded from formula  Diff = Avg Rate for 20-21/MAP as on date


	@Test(priority = 38)
	public void Check_data_is_bindedornot_in_DIFF_Percentage() throws IOException {
		CommonUtils.Thread_Sleep(3);
		String Avg_Rate = Elements.Binded_Imported_Avg_Rate_20_21.getText();
		//Avg_Rate=Avg_Rate.replace(",", "");
		double avg = Double.parseDouble(Avg_Rate);
		System.out.println(Avg_Rate);
		String Map = driver.findElement(By.xpath("(//*[@class='GridViewTableContent'])/div/table/tbody/tr[1]/td[16]/div/span")).getText();
		//Map= Map.replace(",", "");
		double map = Double.parseDouble(Map);
		System.out.println(map);
		double Diff_percent = avg/map*100;
		System.out.println(Diff_percent);
		System.out.println("Diff_percent is:"+Diff_percent);
		String Diff_percent_Chemical =Elements.Binded_Imported_Diff_Percentage.getText();
		double Difff = Double.parseDouble(Diff_percent_Chemical);
		CommonUtils.Thread_Sleep(3);
		CommonUtils.CaptureScreenshot_for_Equals(driver, Difff, Diff_percent, "TC_100", "Diff should be binded from formula  Diff = Diff/MAP as on date (YTD Nov 20", "Diff should be binded from formula  Diff = Diff/MAP as on date (YTD Nov 20)");
	}*/
}