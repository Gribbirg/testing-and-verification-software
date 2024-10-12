package ru.mirea.testingandverificationsoftware.practice.pracice3.bdd

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertThrows
import ru.mirea.testingandverificationsoftware.practice.pracice3.AudioMixer
import ru.mirea.testingandverificationsoftware.practice.pracice3.Clip
import java.io.File

class EmptyClipListSteps {

    private lateinit var clips: List<Clip>

    @Given("список клипов пуст")
    fun clipsIsEmpty() {
        clips = emptyList()
    }

    @When("я пытаюсь смешать клипы")
    fun tryMix() {
        assertThrows(IllegalArgumentException::class.java) {
            AudioMixer.mixAudioClips(clips, File("output.wav"))
        }
    }

    @Then("должно возникнуть исключение")
    fun isException() {
        // Исключение проверяется в шаге "When"
    }
}
