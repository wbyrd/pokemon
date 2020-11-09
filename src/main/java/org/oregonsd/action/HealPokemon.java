package org.oregonsd.action;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.SimpleAction;
import org.oregonsd.context.Pokemon;
import org.oregonsd.context.Trainer;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class HealPokemon extends SimpleAction {

  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) {
    getGameStateService().getTrainer().flatMap(Trainer::getMainPokemon).ifPresent(Pokemon::restoreToFullHealth);
  }

}
