package org.oregonsd.domain;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

/**
 * @author wbyrd
 *
 */
@Value
@Builder(builderClassName="_Builder")
@JsonDeserialize(builder = Species._Builder.class)
public class Species {
  @JsonPOJOBuilder(withPrefix = "")
  public static class _Builder {}
  
  private final String name;
  @Singular
  private final Set<PokemonType> types;
  private final int hitPoints;
  private final int attack;
  private final int specialAttack;
  private final int defense;
  private final int specialDefense;
  private final int speed;
  
  //for now assume all pokemon have one generic move;
  @Builder.Default
  private final int movePower = 40;
}
