package org.oregonsd.action;

import org.oregonsd.action.base.Action;
import org.oregonsd.action.base.NavAction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author wbyrd
 *
 */
@RequiredArgsConstructor
public enum ActionEnum {
  //General purpose actions
  MAIN_MENU(ShowMainMenu.builder()
      .command("main")
      .description("Go to the Main Menu")
      .build()),
  
  BACK(NavAction.builder()
      .command("back")
      .description("Back to the Previous Menu")
      .build()),

  //Main menu items
  QUIT(NavAction.builder()
      .command("quit")
      .description("Quit the Game")
      .build()),
  
  POKEMON(ShowPokemonMenu.builder()
      .command("ranch")
      .description("Check out the Pokémon Ranch")
      .build()),
  
  TRAINER(ShowTrainerMenu.builder()
      .command("guild")
      .description("Visit the Pokémasters' Guild")
      .build()),
  
  BATTLE(ShowBattleMenu.builder()
      .command("battle")
      .description("Take your Pokémon into a fight")
      .build()),
  
  //Pokemon menu items
  LIST_POKEMON(ListPokemon.builder()
      .command("list")
      .description("List All Pokémon")
      .build()),
  ADD_POKEMON(AddPokemon.builder()
      .command("add")
      .description("Catch a New Pokémon")
      .build()),
  REMOVE_POKEMON(RemovePokemon.builder()
      .command("remove")
      .description("Release a Pokémon")
      .build()),
  SELECT_POKEMON(SelectPokemon.builder()
      .command("select")
      .description("Select a Pokémon to train")
      .build()),
  CONVALESCE(HealPokemon.builder()
      .command("heal")
      .description("Restore your main pokemon to full health")
      .build()),
  
  //Pokemaster menu items
  LIST_POKEMASTER(ListTrainer.builder()
      .command("list")
      .description("List All Trainers")
      .build()),
  SWITCH_POKEMASTER(SwitchTrainer.builder()
      .command("select")
      .description("Select an Existing Trainer")
      .build()),
//  ADD_POKEMASTER(AddTrainer.builder()
//      .command("add")
//      .description("Add a New Trainer")
//      .build()),
//  REMOVE_POKEMASTER(RemovePokemaster.builder()
//      .command("retire")
//      .description("Retire your Trainer")
//      .build()),
  
  //Battle menu items
  START_BATTLE(StartBattle.builder()
      .command("engage")
      .description("Start a New Battle")
      .build()),
  ATTACK(Attack.builder()
    .command("attack")
    .description("Attack your opponent")
    .build()),
  ESCAPE(NavAction.builder()
    .command("escape")
    .description("Run away!")
    .build()),

  ;
  
  /* We want the implementation to have an easy reference to the enum,
   * but you have to build references to each serially.  Clean up the state here.
   */
  static {
    for(ActionEnum e : values()) {
      if(e.implementation!=null) e.implementation.setActionEnum(e);
    }
  }

  public static boolean isNotQuit(ActionEnum toTest) {
    return !QUIT.equals(toTest);
  }
  
  @Getter
  private final Action implementation;
}