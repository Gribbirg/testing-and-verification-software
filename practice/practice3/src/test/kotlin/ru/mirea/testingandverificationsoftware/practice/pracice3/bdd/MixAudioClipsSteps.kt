package ru.mirea.testingandverificationsoftware.practice.pracice3.bdd

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertTrue
import ru.mirea.testingandverificationsoftware.practice.pracice3.AudioMixer
import ru.mirea.testingandverificationsoftware.practice.pracice3.Clip
import java.io.File

class MixAudioClipsSteps {

    private lateinit var clip1: File
    private lateinit var clip2: File
    private lateinit var outputFile: File

    @Given("есть два аудиофайла {string} и {string}")
    fun getTwoFiles(path1: String, path2: String) {
        clip1 = File(path1)
        clip2 = File(path2)
        outputFile = File("src/test/resources/audio/output_mix.wav")
    }

    @When("я смешиваю аудиофайлы")
    fun mixAudio() {
        AudioMixer.mixAudioClips(listOf(Clip(clip1, 0.0, 5.0), Clip(clip2, 0.0, 5.0)), outputFile)
    }

    @Then("результирующий аудиофайл должен существовать и содержать данные")
    fun outputExists() {
        assertTrue(outputFile.exists())
        assertTrue(outputFile.length() > 0)
    }
}
