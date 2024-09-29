Feature: Создание микса из аудиофайлов

  Scenario: Пользователь выбирает несколько аудиофайлов для микса
    Given пользователь выбирает аудиофайлы:
      | file                           | start | end |
      | src/test/resources/audio/1.wav | 1.0   | 5.0 |
      | src/test/resources/audio/2.wav | 1.0   | 5.0 |
      | src/test/resources/audio/3.wav | 1.0   | 5.0 |
      | src/test/resources/audio/4.wav | 1.0   | 5.0 |
    When пользователь нажимает "Create mix"
    Then создается файл как "src/test/resources/audio/expected.wav" с результатом