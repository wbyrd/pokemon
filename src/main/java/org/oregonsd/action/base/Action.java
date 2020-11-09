package org.oregonsd.action.base;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.oregonsd.action.ActionContract;
import org.oregonsd.action.ActionEnum;
import org.oregonsd.console.ConsoleUtils;
import org.oregonsd.context.GameContext;
import org.oregonsd.service.GameStateService;
import org.oregonsd.service.PokemonService;
import org.oregonsd.service.SpeciesService;
import org.oregonsd.service.TrainerService;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class Action implements ActionContract {
  private ActionEnum actionEnum;
  private String command;
  private String description;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.PROTECTED)
  private GameContext context;
  
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.PROTECTED)
  private TrainerService trainerService;
  
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.PROTECTED)
  private PokemonService pokemonService;
  
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.PROTECTED)
  private SpeciesService speciesService;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.PROTECTED)
  private GameStateService gameStateService;
    
  private void checkContext() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(trainerService);
    Objects.requireNonNull(pokemonService);
    Objects.requireNonNull(speciesService);
    Objects.requireNonNull(gameStateService);
  }
  
  public ActionEnum runInContext(GameContext context, ActionAndNext actionAndNext) {
    try {
      this.context = context;
      this.trainerService = context.getTrainerService();
      this.speciesService = context.getSpeciesService();
      this.pokemonService = context.getPokemonService();
      this.gameStateService = context.getGameStateService();
      return actionAndNext.call(context.getInputPrompt(), context.getOutputConsumer());
    } finally {
      this.context = null;
      this.trainerService = null;
      this.speciesService = null;
      this.gameStateService = null;
    }
  }

  public void act(Function<String, String> in, Consumer<String> out) {
    checkContext();
    doAct(in, out); 
  }
  
  public ActionEnum next(Function<String, String> in, Consumer<String> out) {
    checkContext();
    return doNext(in, out);
  }

  protected abstract void doAct(Function<String, String> in, Consumer<String> out);  
  protected abstract List<ActionEnum> doActions();
  
  protected ActionEnum doNext(Function<String, String> in, Consumer<String> out) {
    var actions = doActions();
    if (actions == null) return null;

    if (actions.isEmpty()) return actionEnum;

    printAvailableActionsTable(actions, out);
    var selection = in.apply("");
    ConsoleUtils.cls(out);

    if (StringUtils.isBlank(selection)) return actionEnum;

    return actions.stream().filter(firstTokenEqualsIgnoreCase(selection)).findFirst().orElse(actionEnum);

  }
    
  protected void printAvailableActionsTable(List<ActionEnum> actions, Consumer<String> out) {
    if (!actions.isEmpty()) {
      out.accept("\nPlease select an option:\n");
      
      actions.stream()
        .map(actionEnum -> {
          Action toReturn = actionEnum.getImplementation();
          if(toReturn == null) out.accept(String.format("No implementation for %s\n", actionEnum.name()));
          return toReturn;
        })
        .filter(Objects::nonNull)
        .map(action -> String.format(
            "%10s   %s%n", 
            action.getCommand().toLowerCase(), 
            action.description))
        .forEach(out);
    }
  }
  
  private Predicate<ActionEnum> firstTokenEqualsIgnoreCase(String selection) {
    return toTest -> selection.split("[^A-Za-z0-9]")[0].equalsIgnoreCase(toTest.getImplementation().getCommand());
  }  
}