package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int count=0;
	int maxcnt=1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxcnt) {
			count++;
			return true;
		}
		return false;
	}

}
