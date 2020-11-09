package org.oregonsd.game;

import java.util.ArrayDeque;
import java.util.Deque;

import org.oregonsd.action.ActionEnum;
import org.oregonsd.action.base.NavAction;

/**
 * @author wbyrd
 *
 */
public class ActionEnumManager {
  private Deque<ActionEnum> actionDeque= new ArrayDeque<>();
  
  public ActionEnumManager(ActionEnum initial) {
    actionDeque.addFirst(initial);
  }
  
  public void startAction(ActionEnum action) {
    if(!NavAction.class.isAssignableFrom(action.getImplementation().getClass()))
      actionDeque.addFirst(action);
  }
  
  public ActionEnum endAction() {
    ActionEnum toReturn;
    
    if(actionDeque.size() > 1) {
      actionDeque.removeFirst();
      toReturn = actionDeque.getFirst();
    } else {
      toReturn = actionDeque.getFirst();
    }
    
    return toReturn;
  }
  
  public ActionEnum currentAction() {
    return actionDeque.getFirst();
  }
  
  public boolean isActionChanged(ActionEnum choice) {
    return actionDeque.getFirst().equals(choice);
  }
}
