package org.oregonsd.action;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.SimpleAction;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class RemovePokemon extends SimpleAction {

  @Override
  protected void doAct(Function<String, String> inputSupplier, Consumer<String> outputConsumer) {
    // TODO Auto-generated method stub
    
  }

}
