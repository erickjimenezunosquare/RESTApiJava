package Logger;

import org.testng.Assert;

public final class Log 
{
	private static int rowLineId = 1;
	
	//..............................................................................................................................
	//............................................................ PRINT ...........................................................
	//..............................................................................................................................
	
	public static void Print(String message)
	{
		System.out.println(message); 
	}
	
	public static void PrintRowLine()
	{
		Print("_____________________________________________________________________________________________________________________");
	}
	
	public static void PrintRowLineWithId()
	{
		Print("_________________________________________________________ " + rowLineId + " _________________________________________________________");
		rowLineId++;
	}
	
	public static void StartMethod(String methodName)
	{
		PrintRowLineWithId();
		Print("Starting method [" + methodName + "]");
		Print("");
		rowLineId--;
	}
	
	public static void EndMethod(String methodName)
	{		
		Print("");
		Print("End of method [" + methodName + "]");
		PrintRowLineWithId();
	}	
	
	//..............................................................................................................................
	//......................................................... ASSERTIONS .........................................................
	//..............................................................................................................................
	
	public static void AssertAreEqual(String expected, String actual, String message)
	{		
		Assert.assertEquals(actual, expected);
		Print("AssertAreEqual Passed! Expected [" + expected + "] vs Actual [" + actual + "] : " + message);
	}
	
	public static void AssertAreEqual(int expected, int actual, String message)
	{		
		Assert.assertEquals(actual, expected);
		Print("AssertAreEqual Passed! Expected [" + expected + "] vs Actual [" + actual + "] : " + message);
	}
	
	public static void AssertAreEqual(double expected, double actual, String message)
	{		
		Assert.assertEquals(actual, expected);
		Print("AssertAreEqual Passed! Expected [" + expected + "] vs Actual [" + actual + "] : " + message);
	}
	
	public static void AssertAreNotEqual(String notExpected, String actual, String message)
	{		
		Assert.assertNotEquals(actual, notExpected);
		Print("AssertAreNotEqual Passed! Expected [" + notExpected + "] vs Actual [" + actual + "] : " + message);
	}
	
	public static void AssertAreNotEqual(int notExpected, int actual, String message)
	{		
		Assert.assertNotEquals(actual, notExpected);
		Print("AssertAreNotEqual Passed! Expected [" + notExpected + "] vs Actual [" + actual + "] : " + message);
	}
	
	public static void AssertAreNotEqual(double notExpected, double actual, String message)
	{		
		Assert.assertNotEquals(actual, notExpected);
		Print("AssertAreNotEqual Passed! Expected [" + notExpected + "] vs Actual [" + actual + "] : " + message);
	}
	
	public static void AssertIsNull(Object myObject, String message)
	{		
		Assert.assertNull(myObject, message);
		Print("AssertIsNull Passed!: " + message);
	}
	
	public static void AssertIsNotNull(Object myObject, String message)
	{		
		Assert.assertNotNull(myObject, message);
		Print("AssertIsNotNull Passed!: " + message);
	}	
	
	public static void AssertFail(String message)
	{
		Print("AssertFail ocurred due to reason: " + message);
		Assert.fail(message);
	}
}
