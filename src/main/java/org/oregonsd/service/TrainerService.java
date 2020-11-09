package org.oregonsd.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.oregonsd.context.GameState;
import org.oregonsd.context.Pokemon;
import org.oregonsd.context.Trainer;
import org.oregonsd.repository.GameStateRepository;
import org.oregonsd.repository.TrainerRepository;

/**
 * @author wbyrd
 *
 */
public class TrainerService {
  private TrainerRepository trainerRepository;
  private GameStateRepository gameStateRepository;
  
  /**
   * @param trainerRepository
   * @param gameStateRepository
   */
  public TrainerService(TrainerRepository trainerRepository, GameStateRepository gameStateRepository) {
    this.trainerRepository = trainerRepository;
    this.gameStateRepository = gameStateRepository;
  }

  public Trainer createTrainer(String name) {
    var trainer = Trainer.builder()
        .name(name)
        .build();
    
    trainerRepository.add(trainer);
    gameStateRepository.item().setTrainer(trainer);
    
    return trainer;
  }
  
  public List<Trainer> getTrainers() {
    return getTrainers(t->true, t->t).collect(Collectors.toList());
  }

  public <U> Stream<U> getTrainers(Predicate<Trainer> filter, Function<Trainer, U> transformer) {
    return trainerRepository.list().stream().filter(filter) .map(transformer);
  }
  
  public String getTrainerNamesString() {
    return getTrainers(t->true, Trainer::getName).collect(Collectors.collectingAndThen(
        Collectors.joining(", ", "Known Trainers: [ ", " ]"),
        s -> s.length() > 20 ? s : "There are no trainers currently on our roster." ));
  }
  
  public Optional<Trainer> getTrainer(String name) {
    return trainerRepository.findByName(name);
  }

  /**
   * @param pokemon
   * @param trainer
   */
  public void addPokemonToTrainer(Pokemon pokemon, Trainer trainer) {
    trainer.getPokedexs().add(pokemon);
    trainer.setMainPokemon(pokemon);
    gameStateRepository.store();
  }
}
