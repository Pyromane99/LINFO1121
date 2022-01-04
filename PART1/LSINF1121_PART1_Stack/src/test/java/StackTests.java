import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class StackTests {

    @Test
    public void firstTest() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        assertEquals(new Integer(1), stack.pop());
    }

    @Test
    public void secondTest() {
        Stack<Integer> stack = new MyStack<>();
        assertTrue(stack.empty());
        for (int i=0;i<1000;i++){
            stack.push(i);
        }
        assertFalse(stack.empty());
        for (int i = 999; i >= 0; i--) {
            assertEquals(i,(int)stack.peek());
            assertEquals(i,(int)stack.pop());
        }
        assertTrue(stack.empty());
    }

    @Test
    public void errorTest(){
        Stack<Integer> stack = new MyStack<>();
        try {
            int a = stack.pop();
            fail();
        }catch (EmptyStackException e){
            assertTrue(true);
        }

        try {
            int a = stack.peek();
            fail();
        }catch (EmptyStackException e){
            assertTrue(true);
        }
    }

    // Feel free to add tests in this class to debug your program




}

