package scrabble;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.IsEqual.equalTo;

public class BagTest {

  private Bag bag;
  
  @Rule public ExpectedException thrownException = ExpectedException.none();
  
  @Before
  public void setUp() {
    bag = new Bag();
  }
  
  @Test
  public void initialDistribution() {
    String expected = "12: E\n"
        + "9: A, I\n"
        + "8: O\n"
        + "6: N, R, T\n"
        + "4: D, L, S, U\n"
        + "3: G\n"
        + "2: B, C, F, H, M, P, V, W, Y, _\n"
        + "1: J, K, Q, X, Z";
    String actual = bag.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void drawOneA() {
    bag.draw('A');
    String expected = "12: E\n"
        + "9: I\n"
        + "8: A, O\n"
        + "6: N, R, T\n"
        + "4: D, L, S, U\n"
        + "3: G\n"
        + "2: B, C, F, H, M, P, V, W, Y, _\n"
        + "1: J, K, Q, X, Z";
    String actual = bag.toString();
    assertEquals(expected, actual);    
  }

  @Test
  public void drawTwoF() {
    bag.draw('F');
    bag.draw('F');
    String expected = "12: E\n"
        + "9: A, I\n"
        + "8: O\n"
        + "6: N, R, T\n"
        + "4: D, L, S, U\n"
        + "3: G\n"
        + "2: B, C, H, M, P, V, W, Y, _\n"
        + "1: J, K, Q, X, Z\n"
        + "0: F";
    String actual = bag.toString();
    assertEquals(expected, actual);    
  }

  @Test
  public void drawThreeG() {
    bag.draw('G');
    bag.draw('G');
    bag.draw('G');
    String expected = "12: E\n"
        + "9: A, I\n"
        + "8: O\n"
        + "6: N, R, T\n"
        + "4: D, L, S, U\n"
        + "2: B, C, F, H, M, P, V, W, Y, _\n"
        + "1: J, K, Q, X, Z\n"
        + "0: G";
    String actual = bag.toString();
    assertEquals(expected, actual);    
  }

  @Test
  public void drawThreeB() {
    thrownException.expect(IllegalDrawingException.class);
    thrownException.expectMessage(equalTo("Invalid input. More B's have been taken from the bag than possible"));
    bag.draw('B');
    bag.draw('B');
    bag.draw('B');
    String expected = "12: E\n"
        + "9: A, I\n"
        + "8: O\n"
        + "6: N, R, T\n"
        + "4: D, L, S, U\n"
        + "3: G\n"
        + "2: B, C, F, H, M, P, V, W, Y, _\n"
        + "1: J, K, Q, X, Z";
    String actual = bag.toString();
    assertEquals(expected, actual);    
  }
  
  @Test
  public void testPhrase1() {
    String input = "PQAREIOURSTHGWIOAE_";
    bag.draw(input);
    String expected = "10: E\n"
        + "7: A, I\n"
        + "6: N, O\n"
        + "5: T\n"
        + "4: D, L, R\n"
        + "3: S, U\n"
        + "2: B, C, F, G, M, V, Y\n"
        + "1: H, J, K, P, W, X, Z, _\n"
        + "0: Q";
    String actual = bag.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void testPhrase2() {
    String input = "LQTOONOEFFJZT";
    bag.draw(input);
    String expected = "11: E\n"
        + "9: A, I\n"
        + "6: R\n"
        + "5: N, O\n"
        + "4: D, S, T, U\n"
        + "3: G, L\n"
        + "2: B, C, H, M, P, V, W, Y, _\n"
        + "1: K, X\n"
        + "0: F, J, Q, Z";
    String actual = bag.toString();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testPhrase3() {
    String input = "AXHDRUIOR_XHJZUQEE";
    thrownException.expect(IllegalDrawingException.class);
    thrownException.expectMessage(equalTo("Invalid input. More X's have been taken from the bag than possible"));
    bag.draw(input);
    
  }
}
