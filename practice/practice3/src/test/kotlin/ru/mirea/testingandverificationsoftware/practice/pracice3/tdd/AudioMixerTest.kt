package ru.mirea.testingandverificationsoftware.practice.pracice3.tdd

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.mirea.testingandverificationsoftware.practice.pracice3.AudioMixer
import ru.mirea.testingandverificationsoftware.practice.pracice3.Clip
import java.io.File
import javax.sound.sampled.AudioInputStream

class AudioMixerTest {

    @Test
    fun `test mix clips`() {
        val clips = getTestAudioClips()
        val outputFile = getOutputFile()

        AudioMixer.mixAudioClips(clips, outputFile)

        assertOutputFile(outputFile)
    }

    @Test
    fun `test cutAudioClip returns correct audio segment`() {
        val clip = getTestAudioClips().first()
        val cutClip: AudioInputStream = AudioMixer.cutAudioClip(clip)

        assertNotNull(cutClip)
        assertEquals(44100 * 4, cutClip.frameLength)
    }

    @Test
    fun `test mixAudioClips with two files`() {
        val clips = getTestAudioClips().subList(0, 2)
        val outputFile = getOutputFile()

        AudioMixer.mixAudioClips(clips, outputFile)

        assertTrue(outputFile.exists())
        assertTrue(outputFile.length() > 0)
    }

    @Test
    fun `test mixAudioClips with empty list`() {
        val outputFile = getOutputFile()

        assertThrows(IllegalArgumentException::class.java) {
            AudioMixer.mixAudioClips(emptyList(), outputFile)
        }
    }

    @Test
    fun `test cutAudioClip with invalid time range`() {
        val clip = Clip(File("src/test/resources/audio/1.wav"), 20.0, 10.0)

        assertThrows(IllegalArgumentException::class.java) {
            AudioMixer.cutAudioClip(clip)
        }
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