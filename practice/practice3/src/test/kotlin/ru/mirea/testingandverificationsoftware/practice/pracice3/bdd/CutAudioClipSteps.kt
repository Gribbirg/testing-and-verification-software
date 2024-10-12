package ru.mirea.testingandverificationsoftware.practice.pracice3.bdd

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import ru.mirea.testingandverificationsoftware.practice.pracice3.AudioMixer.cutAudioClip
import ru.mirea.testingandverificationsoftware.practice.pracice3.Clip
import java.io.File
import javax.sound.sampled.AudioInputStream


class CutAudioClipSteps {

    private lateinit var clip: Clip
    private lateinit var result: AudioInputStream

    @Given("есть аудиофайл {string} с начальным временем {double} и конечным временем {double}")
    fun getAudio(path: String, startTime: Double, endTime: Double) {
        clip = Clip(File(path), startTime, endTime)
    }

    @When("я обрезаю аудиоклип")
    fun cutAudioClip() {
        result = cutAudioClip(clip)
    }

    @Then("полученный аудиоклип должен иметь длину {int} секунд")
    fun assertAudioLen(expectedLength: Int) {
        assertNotNull(result)
        assertEquals(expectedLength * 44100L, result.frameLength)
    }
}