package no.hvl.dat153;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import no.hvl.dat153.Activity.DatabaseActivity;
import no.hvl.dat153.Activity.GameActivity;
import no.hvl.dat153.Activity.MainActivity;

class MyIdlingResource implements IdlingResource {
    private ResourceCallback resourceCallback;
    private boolean isIdle = false;

    @Override
    public String getName() {
        return "MyIdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
        if (idle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
    }
}


@RunWith(AndroidJUnit4.class)
public class QuizAppTest {

    private ActivityScenario<MainActivity> mainScrenario;
    private ActivityScenario<GameActivity> GameActivity;
    private ActivityScenario<DatabaseActivity> DatabaseActivity;
    private MyIdlingResource myIdlingResource;

    @Before
    public void SetUp(){
        Intents.init();

    }

    //Testing to
    @Test
    public void testMainActivity(){
        mainScrenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.Start)).check(matches(isDisplayed()));

        onView(withId(R.id.Start)).perform(click());

        onView(withId(R.id.alt1)).check(matches(isDisplayed()));

        onView(withId(R.id.alt1)).perform(click());

        mainScrenario.close();
    }

    @Test
    public void testQuizActivity(){

    }


}
