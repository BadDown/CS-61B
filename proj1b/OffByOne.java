/**
 * @author Stunoon
 * @date 2022/5/16 12:36
 * @apiNote
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (x - 1 == y || x == y - 1) {
            return true;
        }
        return false;
    }
}
