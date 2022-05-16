/**
 * @author Stunoon
 * @date 2022/5/16 18:41
 * @apiNote
 */
public class OffByN implements CharacterComparator{
    int distance;

    OffByN(int N) {
        distance = Math.abs(N);
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x-y) == distance) {
            return true;
        }
        return false;
    }
}
