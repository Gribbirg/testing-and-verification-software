package ru.mirea.testingandverificationsoftware.practice.pracice3

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import ru.mirea.testingandverificationsoftware.practice.pracice3.AudioMixer
import ru.mirea.testingandverificationsoftware.practice.pracice3.Clip
import java.io.File

class AudioMixerSteps {

    private val audioMixer = AudioMixer()
    private lateinit var clips: List<Clip>
    private lateinit var outputFile: File

    @Given("пользователь выбирает аудиофайлы:")
    fun `get audio files`(dataTable: List<Map<String, String>>) {
        clips = dataTable.map {
            Clip(
                File(it["file"]!!),
                it["start"]!!.toDouble(),
                it["end"]!!.toDouble()
            )
        }
    }

    @When("пользователь нажимает {string}")
    fun `create mix`(action: String) {
        if (action == "Create mix") {
            outputFile = getOutputFile()
            audioMixer.mixAudioClips(clips, outputFile)
        }
    }

    @Then("создается файл как {string} с результатом")
    fun `check file`(expectedFileName: String) {
        val expectedFile = File(expectedFileName)
        assertTrue(expectedFile.exists())
        assertEquals(expectedFile.readText(), outputFile.readText())
    }

    private fun getOutputFile(): File =
        File("src/test/resources/audio/output_mix.wav").apply {
            if (exists()) {
                delete()
                createNewFile()
            }
        }
}