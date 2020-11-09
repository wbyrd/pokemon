package org.oregonsd.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wbyrd
 *
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Battle {
  private Pokemon opponent;
  private boolean opponentHasInitiative;  
}