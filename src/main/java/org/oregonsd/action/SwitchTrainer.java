package org.oregonsd.action;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.SimpleAction;
import org.oregonsd.context.GameState;
import org.oregonsd.repository.GameStateRepository;
import org.oregonsd.repository.TrainerRepository;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class SwitchTrainer extends SimpleAction {
  
  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) {
    getGameStateService()
      .getTrainer()
      .map(trainer -> {
        out.accept("Oh! We have you listed incorrectly in our roster.\n");
        out.accept(getTrainerService().getTrainerNamesString());
        return getName(in);
      })
      .orElseGet(()->{
        out.accept("Welcome back; let me find your trainer's pass...\n");
        return getName(in);
      })
      .ifPresent(getGameStateService()::setTrainer);      
  }
  
  private Optional<String> getName(Function<String, String> in) {
    return Optional.of(in.apply("What was your name again?"));
  }
}
