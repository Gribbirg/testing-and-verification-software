package ru.mirea.testingandverificationsoftware.practice.pracice3

import java.io.ByteArrayOutputStream
import java.io.File
import javax.sound.sampled.AudioFileFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

class AudioMixer {

    private fun cutAudioClip(clip: Clip): AudioInputStream {
        val inputStream = AudioSystem.getAudioInputStream(clip.file)
        val format = inputStream.format
        val startFrame = (clip.startTime * format.frameRate).toLong()
        val endFrame = (clip.endTime * format.frameRate).toLong()
        val frameSize = format.frameSize

        inputStream.skip(startFrame * frameSize)

        val framesToRead = (endFrame - startFrame).toInt()
        return AudioInputStream(inputStream, format, framesToRead.toLong())
    }

    fun mixAudioClips(clips: List<Clip>, outputFile: File) {
        val audioFormat = AudioSystem.getAudioInputStream(clips[0].file).format

        val audioStreams = clips.map { cutAudioClip(it) }

        val byteArrayOutputStream = ByteArrayOutputStream()

        for (audioStream in audioStreams) {
            val buffer = ByteArray(1024)
            var bytesRead: Int

            while (audioStream.read(buffer).also { bytesRead = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead)
            }

            audioStream.close()
        }

        val combinedStream = AudioInputStream(
            byteArrayOutputStream.toByteArray().inputStream(),
            audioFormat,
            byteArrayOutputStream.size().toLong() / audioFormat.frameSize
        )

        AudioSystem.write(combinedStream, AudioFileFormat.Type.WAVE, outputFile)
    }
}