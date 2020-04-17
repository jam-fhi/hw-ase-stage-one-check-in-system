import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CheckIn.LoggingSingleton;

/**
 * LoggingSingletonTests
 * Tests the logging clas singleton design pattern.
 * @author jamiehill
 *
 */
public class LoggingSingletonTests {

	/**
	 * testSingletonIsSingleton
	 * It will get the same instance of the singleton
	 * twice and by using different variables it will
	 * add a message and assert the objects are the same.
	 */
	@Test
	public void testSingletonIsSingleton() {
		LoggingSingleton LoggingSingletonOne = LoggingSingleton.getInstance();
		LoggingSingleton LoggingSingletonTwo = LoggingSingleton.getInstance();
		LoggingSingletonOne.addLog("Log Message One", "log");
		LoggingSingletonTwo.addLog("Log Message Two", "log");
		assertEquals(LoggingSingletonOne, LoggingSingletonTwo);
	}
}
