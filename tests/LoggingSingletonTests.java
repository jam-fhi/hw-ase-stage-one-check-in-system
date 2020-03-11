import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CheckIn.LoggingSingleton;

public class LoggingSingletonTests {

	@Test
	public void testSingletonIsSingleton() {
		LoggingSingleton LoggingSingletonOne = LoggingSingleton.getInstance();
		LoggingSingleton LoggingSingletonTwo = LoggingSingleton.getInstance();
		LoggingSingletonOne.addLog("Log Message One");
		LoggingSingletonTwo.addLog("Log Message Two");
		assertEquals(LoggingSingletonOne, LoggingSingletonTwo);
	}
}
