package no.hvl.dat153;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import no.hvl.dat153.activities.DatabaseActivity;
import no.hvl.dat153.activities.GameActivity;
import no.hvl.dat153.activities.MainActivity;
import no.hvl.dat153.data.Animal;
import no.hvl.dat153.data.AnimalViewModel;
import no.hvl.dat153.data.TestAnimalRepo;

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

    private ActivityScenario<MainActivity> mainScenario;
    private ActivityScenario<GameActivity> gameScenario;
    private ActivityScenario<DatabaseActivity> databaseScenario;
    private MyIdlingResource myIdlingResource;

    private AnimalViewModel animalViewModel;


    @Before
    public void SetUp() {
        Intents.init();
    }

    //Testing to
    @Test
    public void testMainActivity(){
        mainScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.Start)).check(matches(isDisplayed()));

        onView(withId(R.id.Start)).perform(click());

        onView(withId(R.id.alt1)).check(matches(isDisplayed()));

        onView(withId(R.id.alt1)).perform(click());

        mainScenario.close();

        release();
    }

    @Test
    public void testQuizScoreIsUpdatedWhenAlternativIsPressed(){
        //Launch QUIZ
        gameScenario = ActivityScenario.launch(GameActivity.class);
        onView(withId(R.id.alt1)).check(matches(isDisplayed()));
        onView(withId(R.id.alt1)).perform(click());
        onView(withText("Correct")).inRoot(isDialog()).check(matches(isDisplayed()));
        //Get the Popup box info
        gameScenario.close();
        release();
    }

    @Test
    public void testDatabaseWithUpdateAndDeleteAEntry(){
        TestAnimalRepo fakeRepo = new TestAnimalRepo(ApplicationProvider.getApplicationContext());
        animalViewModel = new AnimalViewModel(ApplicationProvider.getApplicationContext());
        animalViewModel.setRepo(fakeRepo);

        // Get the current list of animals
        List<Animal> animalsBefore = fakeRepo.getAllAnimals().getValue();

        // Create a new animal to insert
        Animal newAnimal = new Animal("Cat", null);

        // Insert the new animal
        animalViewModel.insertAnimal(newAnimal);

        // Get the updated list of animals
        List<Animal> animalsAfter = fakeRepo.getAllAnimals().getValue();

        // Verify that the size of the list has increased by one
        assertEquals(animalsBefore.size() + 1, animalsAfter.size());

        // Verify that the new animal is in the list
        assertTrue(animalsAfter.contains(newAnimal));

        release();
    }


}
