package org.oregonsd.action;

import static org.oregonsd.action.ActionEnum.BATTLE;
import static org.oregonsd.action.ActionEnum.POKEMON;
import static org.oregonsd.action.ActionEnum.QUIT;
import static org.oregonsd.action.ActionEnum.TRAINER;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.MenuAction;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
class ShowMainMenu extends MenuAction {
  
  @Override
  public List<ActionEnum> doActions() {
    return getGameStateService()
        .getTrainer()
        .map(t->{
          List<ActionEnum> toReturn = new ArrayList<>(List.of(QUIT, POKEMON, TRAINER));
          t.getMainPokemon().ifPresent(pokemon -> toReturn.add(BATTLE));
          return toReturn;
        })
        .orElseGet(()->List.of(QUIT, TRAINER));
  }

  @Override
  protected void doActWithMenu(Function<String, String> in, Consumer<String> out) {
    
    getGameStateService()
      .getTrainer()
      .map(trainer -> formatOpt("Welcome Pokémon Trainer %s.  Where will your adventures take you today?%n", trainer.getName()))
      .orElseGet(()->formatOpt("Trainers should visit Pokémaster Guild to register.%n"))
      .ifPresent(out::accept);
  }
  
  private Optional<String> formatOpt(String format, Object...args) {
    return Optional.of(String.format(format, args));
  }
}