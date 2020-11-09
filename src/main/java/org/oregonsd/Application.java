package org.oregonsd;

import java.io.IOException;

import org.oregonsd.action.ActionEnum;
import org.oregonsd.console.ConsoleReader;
import org.oregonsd.context.GameContext;
import org.oregonsd.game.ActionEnumManager;
import org.oregonsd.game.Game;
import org.oregonsd.repository.GameStateRepository;
import org.oregonsd.repository.SpeciesRepository;
import org.oregonsd.repository.TrainerRepository;
import org.oregonsd.service.GameStateService;
import org.oregonsd.service.PokemonService;
import org.oregonsd.service.SpeciesService;
import org.oregonsd.service.TrainerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

/**
 * Load needed utility object, build game context, start game
 * @author wbyrd
 */
public class Application {
  public static void main(String[] args) throws IOException {

    //library utilities
    var mapper = new ObjectMapper().registerModule(new Jdk8Module());
    var reader = new ConsoleReader();
    
    //persistence access
    var speciesRepository = new SpeciesRepository(mapper);
    var trainerRepository = new TrainerRepository(mapper);
    var gameStateRepository = new GameStateRepository(mapper);
    
    //build game state
    var gameContext = GameContext.builder()
      .trainerService(new TrainerService(trainerRepository, gameStateRepository))
      .speciesService(new SpeciesService(speciesRepository))
      .pokemonService(new PokemonService(speciesRepository))
      .gameStateService(new GameStateService(gameStateRepository, trainerRepository))
      .inputPrompt(reader::promptForInput)
      .outputConsumer(System.out::print)
      .build();
    
    var actionEnumManager = new ActionEnumManager(ActionEnum.MAIN_MENU);
    
    // run game
    new Game(gameContext, actionEnumManager).startGame();    
  }
}
