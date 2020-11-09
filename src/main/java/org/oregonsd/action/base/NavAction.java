package org.oregonsd.action.base;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.ActionEnum;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class NavAction extends Action {
  protected List<ActionEnum> doActions() {
    return null;
  }
  
  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) {
    
  }
}