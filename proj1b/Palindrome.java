/**
 * @author Stunoon
 * @date 2022/5/15 15:46
 * @apiNote
 */
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            returnDeque.addLast(word.charAt(i));
        }
        return returnDeque;
    }

/* the first of the method
    public boolean isPalindrome(String word) {
        int len1 = word.length();
        if (len1 == 0 || len1 == 1) {
            return true;
        }
        for (int i = 0; i < len1 / 2; i++) {
            if (word.charAt(i) != word.charAt(len1 - i - 1)) {
                return false;
            }
        }
        return true;
    }
*/

    /* the recommended method in CS61B */
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /** overloaded isPalindrome, decide if the given word is palindrome.
     * according to the given CharacterComparator
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        for (int i = 0; i < d.size() / 2; i++) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                return false;
            }
        }
        return true;
    }

}
