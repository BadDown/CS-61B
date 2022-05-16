import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = (Deque) palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        boolean d = palindrome.isPalindrome("abcdefg");
        boolean e = palindrome.isPalindrome("abba");
        boolean y = palindrome.isPalindrome("");
        assertEquals(false,d);
        assertEquals(true,e);
        assertEquals(true,y);

    }

    @Test
    public void testIsOffByOnePalindrome() {
        CharacterComparator c = new OffByOne();
        assertTrue(palindrome.isPalindrome("aemnfb",c));
        assertTrue(palindrome.isPalindrome("aemanfb",c));
        assertFalse(palindrome.isPalindrome("abca",c));
    }
}
