package com.abnamro.apps.referenceandroid.test

import io.cucumber.junit.CucumberOptions

@CucumberOptions(
        features = ["features"],
        glue = ["com.abnamro.apps.referenceandroid.steps"],
        tags = ["@regression"]
)
class CucumberTestOptions
