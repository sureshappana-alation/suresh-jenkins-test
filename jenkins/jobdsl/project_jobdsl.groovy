import JenkinsTemplateConfig
import JenkinsTemplateCompileJob
import alation.jobdsl.DslEnv
import alation.jobdsl.jobs.*
import alation.jobdsl.jobs.chef.*

// initialize framework
JenkinsTemplateConfig.bind(binding)

if (DslEnv.targetFolderIsProjectRoot()) {
    new GithubPullRequestHookJob().generate()
}

new JenkinsTemplateBuildJob().generate()
new JenkinsTemplateSmokeJob().generate()
new GithubPushHookJob().generate()
