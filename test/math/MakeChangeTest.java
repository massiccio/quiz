/**
 * 
 */
package math;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.JUnit4;

/**
 * Run either as:
 * <i>java -cp .:../lib/junit-4.11.jar:../lib/hamcrest-core-1.3.jar ms.MakeChangeTest</i>
 * <p>
 * or
 * <p>
 * java -cp .:../lib/junit-4.11.jar:../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ms.MakeChangeTest
 * 
 * @author michelemazzucco
 */
@RunWith(JUnit4.class)
public class MakeChangeTest {

    /**
     * Test method for {@link ms.MakeChange#makeChange(int)}.
     */
    @Test
    public final void testMakeChange() {
	Assert.assertEquals(4, MakeChange.makeChange(135));
    }

    /**
     * Test method for {@link ms.MakeChange#makeChange(int)}.
     */
    @Test
    public final void testMakeChangeNoMoney() {
	Assert.assertEquals(0, MakeChange.makeChange(0));
    }

    /**
     * Test method for {@link ms.MakeChange#makeChange(int)}.
     */
    @Test
    public final void testMakeChangeBigBill() {
	Assert.assertEquals(1, MakeChange.makeChange(100));
    }

    /**
     * Test method for {@link ms.MakeChange#makeChange(int)}.
     */
    @Test
    public final void testMakeChangeSmallBill() {
	Assert.assertEquals(1, MakeChange.makeChange(1));
    }

    /**
     * Test method for {@link ms.MakeChange#makeChange(int)}.
     */
    @Test
    public final void testMakeChangeAllBills() {
	Assert.assertEquals(6, MakeChange.makeChange(186));
    }

    /**
     * Test method for {@link ms.MakeChange#makeChange(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testMakeNegative() {
	MakeChange.makeChange(-1);
    }

    public static void main(String[] args) {
	Result result = JUnitCore.runClasses(MakeChangeTest.class);
	for (Failure failure : result.getFailures()) {
	    System.out.println(failure.toString());
	}
    }
}
