package arsenault.rj.homelandsecurity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void MapBuilding() throws Exception {
        String input = "(1,2,3),(4,5,6),(7,8,9)";
        final String expectedOutput = "(1,2,3),(4,5,6),(7,8,9)";
        int[] arr = MainActivity.makeMap(input);
        String output = MainActivity.printArray(arr);
        assertEquals(expectedOutput, output);
    }
}