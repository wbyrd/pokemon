package org.oregonsd.action;

import static org.oregonsd.action.ActionEnum.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.MenuAction;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class ShowTrainerMenu extends MenuAction {

  @Override
  protected List<ActionEnum> doActions() {
    return List.of(LIST_POKEMASTER, SWITCH_POKEMASTER, /* ADD_POKEMASTER, REMOVE_POKEMASTER, */ BACK);
  }

  @Override
  protected void doAct(Function<String, String> in, Consumer<String> out) {
    super.doAct(in, out);
    
    out.accept("You are in the halls of the Pokemasters' guild\n");
  }
  
  
}