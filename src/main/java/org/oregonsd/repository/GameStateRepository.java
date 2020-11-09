package org.oregonsd.repository;

import java.io.IOException;

import org.oregonsd.context.GameState;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wbyrd
 *
 */
public class GameStateRepository extends SingleRepository<GameState> {
  private static final String CONTEXT_FILE = ".gamestate";

  /**
   * @param backingFile
   * @param mapper
   * @throws IOException
   */
  public GameStateRepository(ObjectMapper mapper) throws IOException {
    super(CONTEXT_FILE, mapper);
    
    if(item() == null) {
      item(GameState.builder().build());
      store();
    }
    item().setGameStateRepository(this);
  }
}