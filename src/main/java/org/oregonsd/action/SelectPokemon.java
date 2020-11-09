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
public class SelectPokemon extends SimpleAction {

  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) {
    var species = getSpeciesService().random();
    
    out.accept(String.format("You caught a new %s!\n", species.getName()));
    var name = in.apply("What will you name it?");
    
    getGameStateService().getTrainer().ifPresent(trainer -> {
      var pokemon = getPokemonService().createPokemon(name, species);
      
      getTrainerService().addPokemonToTrainer(pokemon, trainer);
    });
  }

}
