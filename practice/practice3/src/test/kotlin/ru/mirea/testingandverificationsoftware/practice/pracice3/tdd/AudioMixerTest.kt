package ru.mirea.testingandverificationsoftware.practice.pracice3.tdd

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.mirea.testingandverificationsoftware.practice.pracice3.AudioMixer
import ru.mirea.testingandverificationsoftware.practice.pracice3.Clip
import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

class AudioMixerTest {

    @Test
    fun `test cut audio`() {
        val audioMixer = AudioMixer()
        val clip = getTestAudioClip().also { println(it.file.absoluteFile) }

        val res = audioMixer.cutAudioClip(clip)

        assertStream(clip, res)
    }

    @Test
    fun `test mix clips`() {
        val audioMixer = AudioMixer()
        val clips = getTestAudioClips()
        val outputFile = getOutputFile()

        audioMixer.mixAudioClips(clips, outputFile)

        assertOutputFile(clips, outputFile)
    }

    private fun getTestAudioClip(): Clip =
        Clip(File("../../assets/audio/1.wav"), 1.0, 5.0)

    private fun getTestAudioClips(): List<Clip> =
        listOf(
            Clip(File("../../assets/audio/1.wav"), 1.0, 5.0),
            Clip(File("../../assets/audio/2.wav"), 1.0, 5.0),
            Clip(File("../../assets/audio/3.wav"), 1.0, 5.0),
            Clip(File("../../assets/audio/4.wav"), 1.0, 5.0),
        )

    private fun getOutputFile(): File =
        File("../../assets/audio/output_mix.wav").apply {
            if (exists()) {
                delete()
                createNewFile()
            }
        }

    private fun assertStream(clip: Clip, stream: AudioInputStream) {
        assertNotNull(stream.format)

        val expectedFrames = (clip.endTime - clip.startTime) * stream.format.frameRate
        assertEquals(expectedFrames.toLong(), stream.frameLength)
    }

    private fun assertOutputFile(clips: List<Clip>, outputFile: File) {
        assertTrue(outputFile.exists())
        assertTrue(outputFile.isFile)

        val stream = AudioSystem.getAudioInputStream(outputFile)
        val expectedFrames = clips.sumOf { it.endTime - it.startTime } * stream.format.frameRate
        assertEquals(expectedFrames.toLong(), stream.frameLength)
    }
}