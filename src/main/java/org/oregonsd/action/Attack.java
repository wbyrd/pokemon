package org.oregonsd.action;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.SimpleAction;
import org.oregonsd.context.Pokemon;
import org.oregonsd.context.Trainer;
import org.oregonsd.pokemon.PokemonAttackResolver;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class Attack extends SimpleAction {

  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) {
    getGameStateService().getBattle().ifPresent(battle->{
      new PokemonAttackResolver(champion(), battle.getOpponent()).resolve(out, getGameStateService()::endBattle);      
    });

    getGameStateService().getBattle().ifPresent(battle->{
      new PokemonAttackResolver(battle.getOpponent(), champion()).resolve(out, getGameStateService()::endBattle);            
    });
  }
  
  private Pokemon champion() {
    return getGameStateService().getTrainer().flatMap(Trainer::getMainPokemon).get();
  }
}