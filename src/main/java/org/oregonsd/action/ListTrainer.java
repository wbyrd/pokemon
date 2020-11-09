package org.oregonsd.action;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.SimpleAction;
import org.oregonsd.service.TrainerService;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class ListTrainer extends SimpleAction {
  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) { 
    out.accept(getTrainerService().getTrainerNamesString());
  }
}
