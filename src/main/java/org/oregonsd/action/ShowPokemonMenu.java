package org.oregonsd.action;

import static org.oregonsd.action.ActionEnum.*;

import java.util.List;

import org.oregonsd.action.base.MenuAction;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class ShowPokemonMenu extends MenuAction {

  @Override
  protected List<ActionEnum> doActions() {
    return List.of(LIST_POKEMON, SELECT_POKEMON, REMOVE_POKEMON, CONVALESCE, BACK);
  }
}