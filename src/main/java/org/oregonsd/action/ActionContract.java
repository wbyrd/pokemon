package org.oregonsd.action;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wbyrd
 *
 */
public interface ActionContract {
  void act(Function<String, String> in, Consumer<String> out);
  ActionEnum next(Function<String, String> in, Consumer<String> out);
}
