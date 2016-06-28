package scrabble;

public class NonExistingLetterException extends ScrabbleException {
  public NonExistingLetterException(char c) {
    super(c + " is not a valid letter");
  }
}
