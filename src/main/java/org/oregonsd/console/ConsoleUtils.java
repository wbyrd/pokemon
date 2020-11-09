package org.oregonsd.console;

import java.util.function.Consumer;

/**
 * @author wbyrd
 *
 */
public class ConsoleUtils {

  /**
   * For ansi standard terminals, this will clear the screen.  For non-ansi terminals,
   * the net effect is no printed characters.
   * 
   * @param out a Function that can consume a String, usually for printing to some file
   */
  public static void cls(Consumer<String> out) {
    out.accept("\033[H\033[2J\b\b\b\b\b\b\b");
  }
}
