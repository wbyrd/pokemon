package org.oregonsd.service;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.oregonsd.context.Pokemon;
import org.oregonsd.context.Trainer;
import org.oregonsd.domain.Species;
import org.oregonsd.repository.SpeciesRepository;

/**
 * @author wbyrd
 *
 */
public class PokemonService {
  private SpeciesRepository speciesRepository;

  
  /**
   * @param gameStateRepository
   */
  public PokemonService(SpeciesRepository speciesRepository) {
    this.speciesRepository = speciesRepository;
  }

  public Pokemon createPokemon(String name, Species species) {
    return Pokemon.builder()
      .level(1)
      .name(name)
      .species(species)
      .hitPointsIV(newIndividualValue())
      .attackIV(newIndividualValue())
      .specialAttackIV(newIndividualValue())
      .defenseIV(newIndividualValue())
      .specialDefenseIV(newIndividualValue())
      .speedIV(newIndividualValue())
      .build()
      .initialize();
  }
 
  private int newIndividualValue() {
    return (int)(Math.random()*32);
  }
  
  public <U> Stream<U> getSpecies(Predicate<Species> filter, Function<Species, U> transformer) {
    return speciesRepository.list().stream().filter(filter) .map(transformer);
  }
  
  public String getPokemonNamesString() {
    return getSpecies(t->true, Species::getName).collect(Collectors.collectingAndThen(
        Collectors.joining(", ", "Known Species: [ ", " ]"),
        s -> s.length() > 19 ? s : "There are no trainers currently on our roster." ));
  }

}