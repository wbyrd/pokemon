package org.oregonsd.game;

import java.util.function.Consumer;

import org.oregonsd.action.ActionEnum;
import org.oregonsd.console.ConsoleUtils;
import org.oregonsd.context.GameContext;

/**
 * @author wbyrd
 *
 */
public class Game {
  private ActionEnumManager actionEnumManager;
  private GameContext context;
  private Consumer<String> out;
  /**
   * @param prompts
   * @param reader
   * @param allPokemon
   */
  public Game(GameContext context, ActionEnumManager actionEnumManager) {
    this.context = context;
    this.actionEnumManager = actionEnumManager;
    this.out = context.getOutputConsumer();
  }

  public void startGame() {
    showGameGreeting();
    runGameLoop();
    showGameFarewell();
  }
  
  private void showGameGreeting() {
    ConsoleUtils.cls(out);
    String title = 
        "    ____        __                                          ___   ____ ___   ____ \n" + 
        "   / __ \\____  / /_____  ____ ___  ____  ____              |__ \\ / __ \\__ \\ / __ \\\n" + 
        "  / /_/ / __ \\/ //_/ _ \\/ __ `__ \\/ __ \\/ __ \\   ______    __/ // / / /_/ // / / /\n" + 
        " / ____/ /_/ / ,< /  __/ / / / / / /_/ / / / /  /_____/   / __// /_/ / __// /_/ / \n" + 
        "/_/    \\____/_/|_|\\___/_/ /_/ /_/\\____/_/ /_/            /____/\\____/____/\\____/  \n" + 
        "                                                                                  ";

    String greeting = "Welcome to the land of Oregano.  Enjoy your time with our many Pokémon.";
    
    out.accept(String.format("%s%n%n%s%n%n", title, greeting));
  }
  
  private void showGameFarewell() {
    out.accept("Thank you for playing!");    
  }  
  
  private void runGameLoop() {
    var choice = actionEnumManager.currentAction();
    
    do {
      var action = choice.getImplementation();
      
      choice = action.runInContext(context, (cin, cout)->{
        action.act(cin, cout);
        return action.next(cin, cout);
      });
      
      if(choice == null) {
        // Action has ended
        choice = actionEnumManager.endAction();
      } else if(!actionEnumManager.isActionChanged(choice)) {
        // A new action is selected
        actionEnumManager.startAction(choice);
      }
      out.accept("\n\n");
    } while(ActionEnum.isNotQuit(choice));
  }
}