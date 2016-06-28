package scrabble;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A Bag of English Scrabble tiles.
 * @author mermet
 *
 */
public class Bag {
  Map<Character, Integer> content;
  
  /**
   * create a bag filled with all the tiles
   */
  public Bag() {
    content = new HashMap<>();
    init();
  }

  private void init() {
    int[] counts = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
    for (int i = 0; i < 26; i++) {
      content.put((char)('A'+i), counts[i]);
    }
    content.put('_', 2);
  }
  
  /**
   * return the ordered list of letters whose occurrence is equal to a given number.
   * @param count the number we are interested in.
   * @return the list of the letters.
   */
  private List<Character> getKeys(int count) {
    return content.entrySet()
                  .stream()
                  .filter(entry -> entry.getValue() == count)
                  .map(entry -> entry.getKey())
                  .sorted(Character::compareTo)
                  .collect(Collectors.toList());
  }
  
  /**
   * generates, for a given number, the string representing the number followed by the list
   * of letters with this number of occurrences in the bag. 
   * @param count the number considered.
   * @return the string expected.
   */
  private String write(int count) {
    return getKeys(count).stream()
                         .map(x -> Character.toString(x))
                         .collect(Collectors.joining(", ", count +": ", ""));
  }
  
  /**
   * Returns the string representation of the content of the bag. Letters with the same number of tiles
   * remaining are grouped together, in alphabetical order. Moreover, the list is in descending order
   * of the quantity of each tile left in the bag. 
   */
  @Override
  public String toString() {
    Set<Integer> existingCounts = content.values()
                                         .stream()
                                         .collect(Collectors.toSet());
    List<Integer> sortedExistingCounts = existingCounts.stream()
                                                       .sorted((i1, i2) -> i2 - i1)
                                                       .collect(Collectors.toList());
    String result = sortedExistingCounts.stream()
                                        .map(count -> write(count))
                                        .collect(Collectors.joining("\n"));
    return result;
  }

  /**
   * Draws a tile in the bag.
   * @param c the tile drawn.
   * @throws IllegalDrawingException This exception is thrown if there is no more such tile remaining
   * in the bag.
   */
  public void draw(char c) throws IllegalDrawingException {
    Integer oldCount = content.get(c);
    if (oldCount == null) {
      throw new NonExistingLetterException(c);
    }
    if (oldCount == 0) {
      throw new IllegalDrawingException(c);
    }
    int newCount = oldCount - 1;
    content.put(c, newCount);
    
  }

  /**
   * Draws a list of tiles
   * @param input the list of tiles to draw, in order.
   * @throws IllegalDrawingException This exception is thrown as soon as a tile from the input that should
   * be drawn is no more present in the bag.
   */
  public void draw(String input) throws IllegalDrawingException {
    input.chars()
         .mapToObj(i -> (char) i)
         .forEach(c -> draw(c));
  }
}
