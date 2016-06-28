package scrabble;

/**
 * This kind of exception is thrown when an illegal character is given.
 * @author mermet
 *
 */
public class NonExistingLetterException extends ScrabbleException {
  public NonExistingLetterException(char c) {
    super(c + " is not a valid letter");
  }
}
