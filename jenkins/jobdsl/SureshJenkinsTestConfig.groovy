import alation.jobdsl.DslEnv

class SureshJenkinsTestConfig {
    static void bind(binding) {
        DslEnv.bind(binding, 'alation_jenkins_template')
    }
}
