import JenkinsTemplateConfig
import alation.jobdsl.jobs.*

// initialize framework
JenkinsTemplateConfig.bind(binding)

// generate Seed Jobs
new SeedSubfolderJob().generate()
new SeedJob().generate()
