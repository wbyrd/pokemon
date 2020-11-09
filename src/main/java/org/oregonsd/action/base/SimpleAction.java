package org.oregonsd.action.base;

import java.util.List;

import org.oregonsd.action.ActionEnum;

import lombok.experimental.SuperBuilder;

/**
 * @author wbyrd
 *
 */
@SuperBuilder
public abstract class SimpleAction extends Action {

  protected List<ActionEnum> doActions() {
    return null;
  }
}
