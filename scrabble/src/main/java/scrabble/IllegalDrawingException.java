package scrabble;

/**
 * Such an exception is thrown when a tile is indicated to be drawn whereas
 * no such tile remains in the bag.
 * @author mermet
 *
 */
public class IllegalDrawingException extends ScrabbleException {
  private static final long serialVersionUID = 1L;

  public IllegalDrawingException(char c) {
    super("Invalid input. More " + c + "'s have been taken from the bag than possible");
  }
}
