package org.oregonsd.repository;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wbyrd
 *
 */
public abstract class ListRepository<T> {
  private static final Logger logger = LoggerFactory.getLogger(ListRepository.class);
  
  private File backingFile;
  private ObjectMapper mapper;
  private List<T> allItems = new ArrayList<T>();
  
  /**
   * @param backingFile
   * @param mapper
   * @param allItems
   * @throws IOException 
   */
  public ListRepository(String backingFile, ObjectMapper mapper) throws IOException {
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

  public List<T> list() {
    return allItems;
  }

  public boolean add(T toAdd) {
    Objects.requireNonNull(toAdd);
    if(allItems.contains(toAdd)) {
      return false;
    } else { 
      var toReturn = allItems.add(toAdd);
      store();
      return toReturn;
    }
  }
  
  public boolean remove(T toRemove) {
    return allItems.remove(toRemove);
  }
  
  public boolean isEmpty() {
    return allItems.isEmpty();
  }
  
  public final void load() {
    try {
      @SuppressWarnings("unchecked")
      Class<T> clazz = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      
      JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
      allItems = mapper.readValue(backingFile, type);
    } catch (IOException e) {
      logger.warn("Unable to read entities from file {}", backingFile.getName());
      logger.debug("Exception", e);
    }
  }
  
  public void store() {
    try {
      mapper.writeValue(backingFile, allItems);
    } catch (IOException e) {
      logger.warn("Unable to write entities to file {}", backingFile.getName());
      logger.debug("Exception", e);
    }
  }
}