package org.oregonsd.console;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility for reading input from users.  This wraps stdin and handles teardown when the 
 * program completes.
 * 
 * @author wbyrd
 */
public class ConsoleReader {
  private static final Scanner scanner = new Scanner(System.in);  
  {
    Runtime.getRuntime().addShutdownHook(new Thread(()->scanner.close()));
  }

  public String promptForInput(String prompt) {
    if(StringUtils.isNotBlank(prompt)) {
      System.out.print(prompt);
    }
    
    System.out.print(" >>> ");
    
    return scanner.nextLine();
  }
}