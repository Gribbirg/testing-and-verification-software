package ru.mirea.testingandverificationsoftware.practice.pracice3

import java.io.File

fun main() {
    val clips = getAudioClips()
    val outputFile = File("practice/practice3/src/main/resources/audio/output_mix.wav")
    if (outputFile.exists()) {
        outputFile.delete()
        outputFile.createNewFile()
    }

    try {
        AudioMixer.mixAudioClips(clips, outputFile)
        println("Микс создан: ${outputFile.absolutePath}")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun getAudioClips(): List<Clip> {
    val scanner = java.util.Scanner(System.`in`)
    println("Введите количество аудиофайлов для микширования:")
    val fileCount = scanner.nextInt()

    val clips = mutableListOf<Clip>()
    for (i in 1..fileCount) {
        println("Введите имя файла #$i:")
        val fileName = scanner.next()
        println("Введите начало отрезка в секундах для файла #$i:")
        val startTime = scanner.nextDouble()
        println("Введите конец отрезка в секундах для файла #$i:")
        val endTime = scanner.nextDouble()

        clips.add(Clip(File("practice/practice3/src/main/resources/audio/$fileName.wav"), startTime, endTime))
    }
    return clips
}

