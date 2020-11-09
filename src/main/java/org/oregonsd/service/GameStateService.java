package org.oregonsd.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.oregonsd.context.Battle;
import org.oregonsd.context.GameState;
import org.oregonsd.context.Pokemon;
import org.oregonsd.context.Trainer;
import org.oregonsd.repository.GameStateRepository;
import org.oregonsd.repository.TrainerRepository;

import lombok.AllArgsConstructor;

/**
 * @author wbyrd
 *
 */
@AllArgsConstructor
public class GameStateService {
  private GameStateRepository gameStateRepository;
  private TrainerRepository trainerRepository;
  
  public Optional<Trainer> getTrainer() {
    return gameStateRepository.item().getTrainer();
  }
  
  public void setTrainer(Trainer trainer) {
    gameStateRepository.item().setTrainer(trainer);
  }
  
  public Optional<Trainer> setTrainer(String name) {
    return trainerRepository.findByName(name)
        .map(gameStateRepository.item()::setTrainer)
        .flatMap(GameState::getTrainer);
  }
  
  public Optional<Pokemon> getPokemon() {
    return getTrainer().flatMap(Trainer::getMainPokemon);    
  }
  
  public List<Pokemon> getAllPokemon() {
    return getTrainer().map(Trainer::getPokedexs).orElseGet(List::of);
  }
  
  public Optional<Battle> getBattle() {
    return gameStateRepository.item().getBattle();
  }

  public Battle startBattle(Pokemon opponent) {
    var battle = Battle.builder()
      .opponent(opponent)
      .opponentHasInitiative(ThreadLocalRandom.current().nextBoolean())
      .build();
    
    gameStateRepository.item().setBattle(battle);
    
    return battle;
  }
  
  public void endBattle() {
    gameStateRepository.item().setBattle(null);
  }
}