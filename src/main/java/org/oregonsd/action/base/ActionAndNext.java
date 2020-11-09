package org.oregonsd.action.base;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.ActionEnum;

/**
 * @author wbyrd
 *
 */
public interface ActionAndNext {
  ActionEnum call(Function<String, String> in, Consumer<String> out);
}
