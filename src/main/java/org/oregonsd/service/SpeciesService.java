package org.oregonsd.service;

import org.oregonsd.domain.Species;
import org.oregonsd.repository.SpeciesRepository;

/**
 * @author wbyrd
 *
 */
public class SpeciesService {
  SpeciesRepository speciesRepository;

  /**
   * @param speciesRepository
   */
  public SpeciesService(SpeciesRepository speciesRepository) {
    this.speciesRepository = speciesRepository;
  }
  
  public Species createSpecies(String name) {
    var species = Species.builder()
        .name(name)
        .build();
    
    speciesRepository.add(species);
    
    return species;
  }

  public Species getSpecies(String name) {
    return speciesRepository.findByName(name);
  }
  
  public Species random() {
    return getSpecies("Charmander");
  }
}