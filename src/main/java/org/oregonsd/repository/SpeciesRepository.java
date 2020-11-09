package org.oregonsd.repository;

import static org.oregonsd.domain.PokemonType.FIRE;

import java.io.IOException;
import java.util.stream.Collectors;

import org.oregonsd.domain.Species;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author wbyrd
 *
 */
public class SpeciesRepository extends ListRepository<Species> {
  private static final String POKEMON_FILE = ".species";
  
  /**
   * @throws IOException 
   * 
   */
  public SpeciesRepository(ObjectMapper mapper) throws IOException {
    super(POKEMON_FILE, mapper);
    
    if(isEmpty()) {
      add(Species.builder()
          .name("Charmander")
          .type(FIRE)
          .hitPoints(39)
          .attack(52)
          .defense(43)
          .specialAttack(60)
          .specialDefense(50)
          .speed(65)
          .build());
    }
  }

  public String listPokemonNames() {
    return list().stream().map(Species::getName).collect(Collectors.joining(", "));
  }
  
  public Species findByName(String name) {
    return list().stream().filter(p->p.getName().equals(name)).findFirst().orElse(null);
  }
}
