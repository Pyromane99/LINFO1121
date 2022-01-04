import org.junit.Test;

import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class RedBlackTests {

    Random rand = new Random();

    private int[] randomArray(int size){
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i]= rand.nextInt();
        }
        return a;
    }

    private RedBlack<Integer,Integer> randomRedBlack(int size){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        int[] a = randomArray(size);
        int[] b = randomArray(size);
        for (int i = 0; i < size; i++) {
            redBlack.put(a[i],b[i]);
        }
        return redBlack;
    }

    @Test
    public void testSize() {
        RedBlack<Integer,Integer> redBlack = randomRedBlack(1000);
        assertEquals(1000,redBlack.size());
    }

    @Test
    public void testEmpty() {
        RedBlack<Integer,String> redBlack = new MyRedBlack<>();
        assertTrue(redBlack.isEmpty());
    }

    @Test
    public void testGetNull(){
        RedBlack<Integer,String> redBlack = new MyRedBlack<>();
        redBlack.put(5,"Pomme");
        redBlack.put(4,"Vache");
        redBlack.put(2,"Banane");
        redBlack.put(8,"Hélicoptère de combat");
        redBlack.put(3,"Jean Neymar");
        assertNull(redBlack.get(1));
    }

    @Test
    public void testGetError(){
        RedBlack<Integer,Integer> redBlack = randomRedBlack(1000);
        try{
            redBlack.get(null);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void testGet(){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        Hashtable<Integer,Integer> hashtable = new Hashtable<>();
        for (int i = 0; i < 1000; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();
            redBlack.put(a,b);
            hashtable.put(a,b);
        }

        for (Integer a: hashtable.keySet()) {
            assertEquals(hashtable.get(a),redBlack.get(a));
        }

    }

    @Test
    public void testContainsNull(){
        RedBlack<Integer,String> redBlack = new MyRedBlack<>();
        redBlack.put(5,"Pomme");
        redBlack.put(4,"Vache");
        redBlack.put(2,"Banane");
        redBlack.put(8,"Hélicoptère de combat");
        redBlack.put(3,"Jean Neymar");
        assertFalse(redBlack.contains(1));
    }

    @Test
    public void testContainsError(){
        RedBlack<Integer,Integer> redBlack = randomRedBlack(1000);
        try{
            redBlack.contains(null);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void testContains(){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        Hashtable<Integer,Integer> hashtable = new Hashtable<>();
        for (int i = 0; i < 1000; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();
            redBlack.put(a,b);
            hashtable.put(a,b);
        }

        for (Integer a: hashtable.keySet()) {
            assertTrue(redBlack.contains(a));
        }
    }

    @Test
    public void testPutError(){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        try {
            redBlack.put(null,5);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteMinError(){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        try {
            redBlack.deleteMin();
            fail();
        }catch (NoSuchElementException e){
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteMin(){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        for (int i = 0; i < 1000; i++) {
            redBlack.put(i,i);
        }
        for (int i = 0; i < 1000; i++) {
            redBlack.deleteMin();
            assertFalse(redBlack.contains(i));
        }
    }

    @Test
    public void testDeleteMaxError(){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        try {
            redBlack.deleteMax();
            fail();
        }catch (NoSuchElementException e){
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteMax(){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        for (int i = 0; i < 1000; i++) {
            redBlack.put(i,i);
        }
        for (int i = 999; i >=0; i--) {
            redBlack.deleteMax();
            assertFalse(redBlack.contains(i));
        }
    }

    @Test
    public void testDeleteError(){
        RedBlack<Integer,Integer> redBlack = randomRedBlack(1000);
        try {
            redBlack.delete(null);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void testDelete(){
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        for (int i = 0; i < 1000; i++) {
            redBlack.put(i,i);
        }
        for (int i = 0; i < 1000; i++) {
            redBlack.delete(i);
            assertFalse(redBlack.contains(i));
        }
    }

    @Test
    public void testHeight(){
        int[] a = {1,5,7,9,10,23,7,6,4,9,4,5};
        RedBlack<Integer,Integer> redBlack = new MyRedBlack<>();
        for (int elem:a) {
            redBlack.put(elem,elem);
        }
        assertEquals(3,redBlack.height());
    }

    


}