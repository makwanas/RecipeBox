package CS561.recipebox;

import android.util.Log;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.tabs.TabLayout;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CS561.recipebox.Inventory.InventoryContractHelper;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class RecipeTabAndInstructionsTests
{
    int main()
    {
        Log.d("Test", "Test");
        try
        {
            testAddAndRemove();
        }
        catch (Exception e)
        {

        }
        return 0;
    }

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);



    @Test
    public void testAddAndRemove() throws Exception
    {
        Log.d("Test", "Test executed.");
        MainActivity activity = rule.getActivity();
        View tabLayout = activity.findViewById(R.id.tabs);
        assertThat(tabLayout,notNullValue());
        assertThat(tabLayout, instanceOf(TabLayout.class));
        Random rand = new Random();
        List<String> testInput = new ArrayList<String>();
        List<Integer> testNum = new ArrayList<Integer>();
        InventoryContractHelper helper = new InventoryContractHelper(activity.getApplicationContext());
        List<String[]> verify;

        testInput.add("A");
        testInput.add("fries");
        testInput.add("fRies");
        testInput.add("FRIES");
        testInput.add("");
        testInput.add("sTEAK");
        testInput.add("STEAK");



        for (int i = 0; i < 1; i++)
        {

            Espresso.onView(withId(R.id.searchView)).perform(clickPosition(activity,20,20));
            Espresso.onView(withId(R.id.searchView)).perform(typeText(testInput.get(i) + "\n"));
            Espresso.onView(withId(R.id.searchView)).perform(clickPosition(activity,500,500));
            Espresso.onView(withId(R.id.recipeTabs)).perform(clickPosition(activity,50,0));
        }
    }

    public static ViewAction clickPosition (MainActivity activity, int x, int y)
    {
        CoordinatesProvider coordProvider = new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view)
            {
                return coords(activity, x, y);
            }
        };
        return new GeneralClickAction(Tap.SINGLE, coordProvider, Press.FINGER);
    }

    public static float[] coords(MainActivity activity, int x, int y)
    {
        int[] screenCoords = new int[2];
        View view = activity.findViewById(R.id.searchView);
        view.getLocationOnScreen(screenCoords);
        float[] coordinates = {screenCoords[0] + x, screenCoords[1] + y};
        return coordinates;
    }
}
