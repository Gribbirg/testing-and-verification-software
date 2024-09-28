package ru.mirea.testingandverificationsoftware.practice.pracice3.tdd

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.mirea.testingandverificationsoftware.practice.pracice3.AudioMixer
import ru.mirea.testingandverificationsoftware.practice.pracice3.Clip
import java.io.File

class AudioMixerTest {

    @Test
    fun `test mix clips`() {
        val audioMixer = AudioMixer()
        val clips = getTestAudioClips()
        val outputFile = getOutputFile()

        audioMixer.mixAudioClips(clips, outputFile)

        assertOutputFile(outputFile)
    }

    private fun getTestAudioClips(): List<Clip> =
        listOf(
            Clip(File("src/test/resources/audio/1.wav"), 1.0, 5.0),
            Clip(File("src/test/resources/audio/2.wav"), 1.0, 5.0),
            Clip(File("src/test/resources/audio/3.wav"), 1.0, 5.0),
            Clip(File("src/test/resources/audio/4.wav"), 1.0, 5.0),
        )

    private fun getOutputFile(): File =
        File("src/test/resources/audio/output_mix.wav").apply {
            if (exists()) {
                delete()
                createNewFile()
            }
        }

    private fun assertOutputFile(outputFile: File) {
        assertTrue(outputFile.exists())
        assertTrue(outputFile.isFile)

        val expected = File("src/test/resources/audio/expected.wav")
        assertEquals(expected.readText(), outputFile.readText())
    }
}