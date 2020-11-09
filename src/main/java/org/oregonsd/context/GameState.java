package org.oregonsd.context;

import java.util.Optional;

import org.oregonsd.repository.GameStateRepository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * @author wbyrd
 *
 */
@Data
@Builder(builderClassName="_Builder")
@JsonDeserialize(builder = GameState._Builder.class)
public class GameState {
  @JsonPOJOBuilder(withPrefix = "")
  public static class _Builder {}
  
  @JsonIgnore
  private GameStateRepository gameStateRepository;
  
  private Trainer trainer;
  private Battle battle;
  
  public Optional<Trainer> getTrainer() {
    return Optional.ofNullable(trainer);
  }
  
  public GameState setTrainer(Trainer trainer) {
    this.trainer = trainer;
    gameStateRepository.store();
    return this;
  }

  public Optional<Battle> getBattle() {
    return Optional.ofNullable(battle);
  }
  
  public GameState setBattle(Battle battle) {
    this.battle = battle;
    gameStateRepository.store();
    return this;
  }

}
