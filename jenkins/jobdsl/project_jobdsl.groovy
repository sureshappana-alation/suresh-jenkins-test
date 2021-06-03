import SureshJenkinsTestConfig
import SureshJenkinsTestCompileJob
import alation.jobdsl.DslEnv
import alation.jobdsl.jobs.*
import alation.jobdsl.jobs.chef.*

// initialize framework
SureshJenkinsTestConfig.bind(binding)

if (DslEnv.targetFolderIsProjectRoot()) {
    new GithubPullRequestHookJob().generate()
}

new SureshJenkinsTestBuildJob().generate()
new SureshJenkinsTestSmokeJob().generate()
new GithubPushHookJob().generate()
