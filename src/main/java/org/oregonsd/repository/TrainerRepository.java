package org.oregonsd.repository;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.oregonsd.context.Trainer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wbyrd
 *
 */

public class TrainerRepository extends ListRepository<Trainer> {
  private static final String CONTEXT_FILE = ".trainer";
  
  /**
   * @param backingFile
   * @param mapper
   * @throws IOException
   */
  public TrainerRepository(ObjectMapper mapper) throws IOException {
    super(CONTEXT_FILE, mapper);
    
    if(isEmpty())
      add(Trainer.builder().name("Winfred").build());
  }
  
  public String listContextNames() {
    return list().stream().map(Trainer::getName).collect(Collectors.joining(", "));
  }
  
  public Optional<Trainer> findByName(String name) {
    return list().stream().filter(p->p.getName().equals(name)).findFirst();
  }
}