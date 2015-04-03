package lu.uni.fstc.algo3;

/**
 * Simple generator to generate unique IDs for Luxembourg Toll System.
 * Created by Gatis on 03/04/2015.
 */
public class IdGenerator {
    private static long id;
    public IdGenerator() {
        id = 0L;
    }
    public long nextID() {
        return id++;
    }
}
