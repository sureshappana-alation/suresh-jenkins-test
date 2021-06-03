import JenkinsTemplateCompileJob
import alation.jobdsl.DslEnv
import alation.jobdsl.jobs.BuildJob

class JenkinsTemplateSmokeJob extends BuildJob {

    // allow override of job name
    protected JenkinsTemplateSmokeJob(jobName = BuildJob.jobSmoke) {
        super(jobName)
    }

    @Override
    protected void generateInstance() {
        super.generateInstance()

        this.environmentVariables.envs['VERSION_FILE'] = 'version.py'
        this.environmentVariables.envs['SHELL_DIR'] = '${WORKSPACE}/jenkins/shell'

        this.steps {
            shell('${SHELL_DIR}/JenkinsTemplateSmokeJob.sh')
        }
    }
}
