package org.oregonsd.action;

import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.SimpleAction;
import org.oregonsd.service.TrainerService;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class AddTrainer extends SimpleAction {
  private TrainerService trainerService;
  
  public void inject(TrainerService trainerService) {
    this.trainerService = trainerService;
  }

  
  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) {
    
    
    StringBuilder builder = new StringBuilder();
    builder.append("\n\n")
      .append("Welcome, friend!\n")
      .append("Let's get you registered as a Pokémon trainer and issued a pass to Oregaon's facilities.\n")
      .append("Then we can discuss the liscensing fee....\n\n");
    
    out.accept(builder.toString());

    String trainerName;
    do {
      trainerName = in.apply("What is your name?");
      if(getTrainerService().getTrainer(trainerName) != null) {
        out.accept("I'm sorry, you seem to be already registered.");
        trainerName = null;
      }
    } while(trainerName == null);

    trainerService.createTrainer(trainerName);
  }
}
