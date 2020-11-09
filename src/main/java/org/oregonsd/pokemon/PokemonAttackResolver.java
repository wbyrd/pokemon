package org.oregonsd.pokemon;

import java.util.function.Consumer;

import org.oregonsd.context.Pokemon;

/**
 * @author wbyrd
 *
 */
public class PokemonAttackResolver {
  private Pokemon attacker;
  private Pokemon defender;
  
  /**
   * @param attacker
   * @param defender
   */
  public PokemonAttackResolver(Pokemon attacker, Pokemon defender) {
    this.attacker = attacker;
    this.defender = defender;
  }
  
  public void resolve(Consumer<String> out, Runnable onFaint) {
    int damage = attacker.attack(defender);
    out.accept(String.format("%1$s attacks %2$s for %3$d damage.  %2$s has %4$d hit points remaining.", 
        attacker.getName(), 
        defender.getName(), 
        damage, 
        defender.getHitPoints()));
    
    if(defender.getHitPoints() < 1) {
      out.accept(String.format("%s has fainted!", defender.getName()));
      onFaint.run();
    }
  }
}
