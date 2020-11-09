package org.oregonsd.repository;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wbyrd
 *
 */
public abstract class SingleRepository<T> {
  private static final Logger logger = LoggerFactory.getLogger(SingleRepository.class);
  
  private File backingFile;
  private ObjectMapper mapper;
  private T item;

  /**
   * @param backingFile
   * @param mapper
   * @param allItems
   * @throws IOException 
   */
  public SingleRepository(String backingFile, ObjectMapper mapper) throws IOException {
    this.mapper = mapper;
    this.backingFile = new File(backingFile);
    
    if(!this.backingFile.exists()) {
      try {
        this.backingFile.createNewFile();
        store();
      } catch (IOException e) {
        logger.warn("Unable to initialize repository in file {}", backingFile);
        throw e;
      }
    } else {
      load();
    }    
  }
  
  public T item() {
    return item;
  }
  
  public void item(T t) {
    Objects.requireNonNull(t);
    item = t;
  }
  
  public final void load() {
    @SuppressWarnings("unchecked")
    Class<T> clazz = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    try {
      item = mapper.readValue(backingFile, clazz);
    } catch (IOException e) {
      logger.warn("Unable to read entities from file {}", backingFile.getName());
      logger.debug("Exception", e);
    }
  }
  
  public void store() {
    try {
      mapper.writeValue(backingFile, item);
    } catch (IOException e) {
      logger.warn("Unable to write entities to file {}", backingFile.getName());
      logger.debug("Exception", e);
    }
  }

}
