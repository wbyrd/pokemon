package org.oregonsd.pokemon;

/**
 * @author wbyrd
 *
 */
public class PokemonNameGenerator {
  private String[] pre = {"Chill", "Lem", "Vul", "Meg", "Coyo"};
  private String[] post = {"ossum", "ith", "tita", "oon", "tuff"};
  
  public String name() {
    return String.format(
        "%s%s", 
        pre[(int)(Math.random()*pre.length)],
        post[(int)(Math.random()*post.length)]);
  }
}
