import alation.jobdsl.DslEnv

class JenkinsTemplateConfig {
    static void bind(binding) {
        DslEnv.bind(binding, 'alation_jenkins_template')
    }
}
