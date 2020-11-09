package org.oregonsd.action;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.SimpleAction;
import org.oregonsd.context.Pokemon;
import org.oregonsd.pokemon.PokemonAttackResolver;
import org.oregonsd.pokemon.PokemonNameGenerator;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class StartBattle extends SimpleAction {

  @Override
  protected void doAct(Function<String, String> in , Consumer<String> out) {
    var gen = new PokemonNameGenerator();
    var species = getSpeciesService().random();
    
    var instance = Pokemon.builder()
        .name(gen.name())
        .species(species)
        .hitPointsIV(Pokemon.newIndividualValue())
        .attackIV(Pokemon.newIndividualValue())
        .specialAttackIV(Pokemon.newIndividualValue())
        .defenseIV(Pokemon.newIndividualValue())
        .specialDefenseIV(Pokemon.newIndividualValue())
        .speedIV(Pokemon.newIndividualValue())
        .build();
    
    instance.initialize();
    
    var battle = getGameStateService().startBattle(instance);
    
    if(!battle.isOpponentHasInitiative()) {
      out.accept(String.format("%s got the jump on you!", battle.getOpponent().getName()));
      new PokemonAttackResolver(instance, getGameStateService().getTrainer().get().getMainPokemon().get())
        .resolve(out, getGameStateService()::endBattle);
    }
  }

}
