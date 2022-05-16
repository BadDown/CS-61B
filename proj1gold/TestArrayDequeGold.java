import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayDeque;


/**
 * @author Stunoon
 * @date 2022/5/16 20:07
 * @apiNote
 */
public class TestArrayDequeGold {


/*    this test is not a good test , because the add function can't not be known if something is wrong!



    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> test1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> test2 = new ArrayDequeSolution<>();
        int size = 0;
        int additem = 0;
        int remove1 = 0;
        int remove2 = 0;
        String log = "";
        for (int i = 0; i < 5000; i++) {
            additem = StdRandom.uniform(5000);
            if (size == 0) {
                test1.addFirst(additem);
                test2.addFirst(additem);
                size++;
            }
            switch (StdRandom.uniform(1,5)){
                case 1:
                    test1.addFirst(additem);
                    test2.addFirst(additem);
                    size++;
                    break;
                case 2:
                    test1.addLast(additem);
                    test2.addLast(additem);
                    size++;
                    break;
                case 3:
                    remove1 = test1.removeFirst();
                    remove2 = test2.removeFirst();
                    log = "First";
                    size--;
                    break;
                case 4:
                    remove1 = test1.removeLast();
                    remove2 = test2.removeLast();
                    log = "Last";
                    size--;
                    break;
                default:
                    System.out.println("StdRandom.uniform error");
            }
            assertEquals("Oh,When it starts to remove" + log + ",the remove1:" + remove1 + " can't equal with the remove2:" + remove2,remove1,remove2);
        }
    }*/


    /*
    * the test can know all the method whether has some errors!
    * */
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdArray = new ArrayDequeSolution<>();
        String log = "";
        for (int i = 0; i < 1000; i++) {
            if (stdArray.size() == 0) {
                int addNumber = StdRandom.uniform(1000);
                int headOrBack = StdRandom.uniform(2);
                if (headOrBack == 0) {
                    log = log + "addFirst(" + addNumber + ")\n";
                    testArray.addFirst(addNumber);
                    stdArray.addFirst(addNumber);
                } else {
                    log = log + "addLast(" + addNumber + ")\n";
                    testArray.addLast(addNumber);
                    stdArray.addLast(addNumber);
                }
            } else {
                int x = StdRandom.uniform(4);
                int addNumber = StdRandom.uniform(1000);
                Integer testremoveNumber = 1;
                Integer stdremoveNumber = 1;
                switch (x) {
                    case 0:
                        log = log + "addFirst(" + addNumber + ")\n";
                        testArray.addFirst(addNumber);
                        stdArray.addFirst(addNumber);
                        break;
                    case 1:
                        log = log + "addLast(" + addNumber + ")\n";
                        testArray.addLast(addNumber);
                        stdArray.addLast(addNumber);
                        break;
                    case 2:
                        log = log + "removeFirst()\n";
                        testremoveNumber = testArray.removeFirst();
                        stdremoveNumber = stdArray.removeFirst();
                        break;
                    case 3:
                        log = log + "removeLast()\n";
                        testremoveNumber = testArray.removeLast();
                        stdremoveNumber = stdArray.removeLast();
                        break;
                    default:
                }
                assertEquals(log, stdremoveNumber, testremoveNumber);
            }
        }
    }
}
