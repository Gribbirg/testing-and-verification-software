package ru.mirea.testingandverificationsoftware.practice.pracice3.bdd

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/features"],
    glue = ["ru.mirea.testingandverificationsoftware.practice.pracice3.bdd"],
)
class RunCucumberTest