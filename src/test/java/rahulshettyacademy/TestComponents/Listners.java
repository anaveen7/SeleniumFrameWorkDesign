package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listners extends BaseTest implements ITestListener{
	
	ExtentReports extent= ExtentReporterNG.getReporterObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extenttest= new ThreadLocal<ExtentTest>();
	 @Override		
	    public void onTestStart(ITestResult result) {					
	        // TODO Auto-generated method stub				
	        	test=extent.createTest(result.getMethod().getMethodName());
	        	extenttest.set(test);
	    }		
	 @Override		
	    public void onTestSuccess(ITestResult result) {					
	        // TODO Auto-generated method stub				
		 extenttest.get().log(Status.PASS, "Test Passed");
	    }
    @Override		
    public void onTestFailure(ITestResult result) {					
        // TODO Auto-generated method stub	
    	extenttest.get().fail(result.getThrowable());
        		
        		try {
					driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
						| SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
				String filepath=null;
        		try {
        			 filepath=getScreenShot(result.getMethod().getMethodName(),driver);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		extenttest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }		

    @Override		
    public void onTestSkipped(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }	
   
    @Override  
    public void onFinish(ITestContext result) {  
    // TODO Auto-generated method stub  
    	extent.flush();
    }	
   

}
