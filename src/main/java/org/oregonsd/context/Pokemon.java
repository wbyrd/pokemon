package org.oregonsd.context;

import java.util.concurrent.ThreadLocalRandom;

import org.oregonsd.domain.Species;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * @author wbyrd
 *
 */
@Data
@Builder(builderClassName="_Builder")
@JsonDeserialize(builder = Pokemon._Builder.class)
public class Pokemon {
  @JsonPOJOBuilder(withPrefix = "")
  public static class _Builder {}

  public static int newIndividualValue() {
    return (int)(Math.random()*32);
  }
  
  private final String name;
  private final Species species;
  private final int hitPointsIV;
  private final int attackIV;
  private final int specialAttackIV;
  private final int defenseIV;
  private final int specialDefenseIV;
  private final int speedIV;
  
  private int level;
  private int hitPoints;
  private int attack;
  private int specialAttack;
  private int defense;
  private int specialDefense;
  private int speed;
  
  public Pokemon initialize() {
    hitPoints = computeHitPoints();
    attack = computeStat(species.getAttack(), attackIV);
    specialAttack = computeStat(species.getSpecialAttack(), specialAttackIV);
    defense = computeStat(species.getDefense(), defenseIV);
    specialDefense = computeStat(species.getSpecialDefense(), specialDefenseIV);
    speed = computeStat(species.getSpeed(), speedIV);
    
    return this;
  }
  
  private int computeHitPoints() {
    return ((int)((2 * species.getHitPoints() + hitPointsIV)/100)) + level + 10;
  }
  
  private int computeStat(int base, int iv) {
    return ((int)((2 * base + iv) * level)/100) + 5;
  }
  
  public int attack(Pokemon defender) {
    var modifier = ThreadLocalRandom.current().nextInt(85, 101) / 100.0;
    var damage = (int)(((2.0 * level / 5 + 2) * species.getMovePower() * (double)attack / defender.defense / 50 + 2) * modifier); 
    defender.hitPoints -= damage > defender.hitPoints ? defender.hitPoints : damage;
    return damage;
  }
  
  public void restoreToFullHealth() {
    hitPoints = computeHitPoints();
  }
  
}
