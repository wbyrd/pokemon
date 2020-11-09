package org.oregonsd.action;

import static org.oregonsd.action.ActionEnum.ATTACK;
import static org.oregonsd.action.ActionEnum.BACK;
import static org.oregonsd.action.ActionEnum.ESCAPE;
import static org.oregonsd.action.ActionEnum.START_BATTLE;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.oregonsd.action.base.MenuAction;
import org.oregonsd.context.Pokemon;
import org.oregonsd.pokemon.PokemonAttackResolver;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class ShowBattleMenu extends MenuAction {

  @Override
  protected List<ActionEnum> doActions() {
    return getGameStateService()
        .getBattle()
        .map(t->List.of(ATTACK, ESCAPE))
        .orElseGet(()->List.of(START_BATTLE, BACK));
  }
}