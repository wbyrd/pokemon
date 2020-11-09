package org.oregonsd.action;

import java.util.List;

import org.oregonsd.action.base.MenuAction;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public class BackMenu extends MenuAction {
  @Override
  protected List<ActionEnum> doActions() {
    return null;
  }
}