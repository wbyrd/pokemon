package org.oregonsd.context;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.service.GameStateService;
import org.oregonsd.service.PokemonService;
import org.oregonsd.service.SpeciesService;
import org.oregonsd.service.TrainerService;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class GameContext {
  private SpeciesService speciesService;
  private PokemonService pokemonService;
  private TrainerService trainerService;
  private GameStateService gameStateService;
  private Consumer<String> outputConsumer;
  private Function<String, String> inputPrompt;
}
