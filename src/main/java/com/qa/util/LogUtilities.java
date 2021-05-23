package com.qa.util;
import org.apache.log4j.Logger;
 
public class LogUtilities {
	
	private LogUtilities() {
	    throw new IllegalStateException("Utility class");
	  }
	
// Initialize Log4j logs
     private static Logger log = Logger.getLogger(LogUtilities.class.getName()); 
// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
public static void startTestCase(String sTestCaseName){

    log.info("****************************************************************************************");
    log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
    log.info("****************************************************************************************");

    }
 
    //This is to print log for the ending of the test case
public static void endTestCase(String eTestCaseName){
    log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
    log.info("X");
    log.info("X");
    log.info("X");
    log.info("X");
    }
 
public static String getTestCaseName(String sTestCase) throws Exception
{
String value = sTestCase;
try{
int posi = value.indexOf('@');
value = value.substring(0, posi);
posi = value.lastIndexOf('.');	
value = value.substring(posi + 1);
return value;
}catch (Exception e){
log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
throw (e);
}
}

    // Need to create these methods, so that they can be called  
public static void info(String message) {
        log.info(message);
        }
public static void warn(String message) {
    log.warn(message);
    }
public static void error(String message) {
    log.error(message);
    }
public static void fatal(String message) {
    log.fatal(message);
    }
public static void debug(String message) {
    log.debug(message);
    }
}
