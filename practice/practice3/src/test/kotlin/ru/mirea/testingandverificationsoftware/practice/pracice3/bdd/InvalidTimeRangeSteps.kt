package ru.mirea.testingandverificationsoftware.practice.pracice3.bdd

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertThrows
import ru.mirea.testingandverificationsoftware.practice.pracice3.AudioMixer
import ru.mirea.testingandverificationsoftware.practice.pracice3.Clip
import java.io.File

class InvalidTimeRangeSteps {

    private lateinit var clip: Clip

    @Given("аудиофайл {string} с началом {double} секунд и концом {double} секунд")
    fun wrongTimes(way: String, start: Double, end: Double) {
        clip = Clip(File(way), start, end)
    }

    @When("я пытаюсь обрезать аудиоклип")
    fun tryCut() {
        assertThrows(IllegalArgumentException::class.java) {
            AudioMixer.cutAudioClip(clip)
        }
    }
}
