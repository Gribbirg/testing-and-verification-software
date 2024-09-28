package ru.mirea.testingandverificationsoftware.practice.pracice3

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/features"],
    glue = ["AudioMixerSteps"]
)
class RunCucumberTest