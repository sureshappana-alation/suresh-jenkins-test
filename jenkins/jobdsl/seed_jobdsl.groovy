import SureshJenkinsTestConfig
import alation.jobdsl.jobs.*

// initialize framework
SureshJenkinsTestConfig.bind(binding)

// generate Seed Jobs
new SeedSubfolderJob().generate()
new SeedJob().generate()
