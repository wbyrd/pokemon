package org.oregonsd.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

/**
 * @author wbyrd
 *
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(builderClassName="_Builder")
@JsonDeserialize(builder = Trainer._Builder.class)
public class Trainer {
  @JsonPOJOBuilder(withPrefix = "")
  public static class _Builder {}

  @EqualsAndHashCode.Include
  private final String name;
  @Builder.Default
  private final List<Pokemon> pokedexs = new ArrayList<Pokemon>();
  private Pokemon mainPokemon;
  
  public Optional<Pokemon> getMainPokemon() {
    return Optional.ofNullable(mainPokemon);
  }
  
  public Trainer setMainPokemon(Pokemon pokemon) {
    if(!pokedexs.contains(pokemon)) 
      throw new IllegalStateException("Pokemon notin pokedex");
    
    mainPokemon = pokemon;
    return this;
  }
  
}
