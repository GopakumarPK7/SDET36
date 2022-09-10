package org.tyss.genericUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener
{
private ExtentReports report;
private ExtentTest test;
private static ExtentTest testLog;
private JavaUtility javaUtility=new JavaUtility();

//Before Method
	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		test.assignAuthor("GK");
		test.assignCategory("Smoke");
		test.assignDevice("Lenovo");
		testLog=test;
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+"Failed");
		test.fail(result.getThrowable());
		TakesScreenshot ts = (TakesScreenshot)BaseClass.listenerdriver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File("./errorshots/"+result.getMethod().getMethodName()+","+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png");
		try {
			FileUtils.copyFile(src, trg);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		String path="./errorshots/"+result.getMethod().getMethodName()+","+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png";
		test.addScreenCaptureFromPath(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}
//Before test
	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark=new ExtentSparkReporter("./extentreport/emailablereport.html");
		spark.config().setDocumentTitle("Tyss");
		spark.config().setReportName("Report");
		spark.config().setTheme(Theme.DARK);
		
		 report=new ExtentReports();
		 report.attachReporter(spark);
		 report.setSystemInfo("OS", "Windows 10");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
