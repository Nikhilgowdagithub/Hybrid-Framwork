package generalUtilitys;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryMechnisam implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (count < maxTry) {
			count++;
		}

		return false;
	}

}
