package org.oregonsd.action.base;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.context.Battle;
import org.oregonsd.context.Trainer;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public abstract class MenuAction extends Action {

  protected void doActWithMenu(Function<String, String> in, Consumer<String> out) {
    
  }
  
  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) {
    printMenuHeader(out);
    doActWithMenu(in, out);
  }
  
  private void printMenuHeader(Consumer<String> out) {
    var gameStateService = getGameStateService();
    
    StringBuilder msg = new StringBuilder();
    
    gameStateService.getTrainer().ifPresent(logTrainer(msg));
    
    gameStateService.getBattle().ifPresent(logBattle(msg));
    
    out.accept(msg.toString());
  }
  
  private Consumer<Trainer> logTrainer(StringBuilder builder) {
    return t -> {
      builder.append(String.format("Trainer: %s%n", t.getName()));
      t.getMainPokemon().ifPresent(p->builder.append(String.format("Pokémon: %s (%s)%n", p.getName(), p.getSpecies().getName())));
    };
  }
    
  private Consumer<Battle> logBattle(StringBuilder builder) {
    return b->builder.append(String.format("%nCurrent Opponent: %s (%s)%n", b.getOpponent().getName(), b.getOpponent().getSpecies().getName()));
  }
}