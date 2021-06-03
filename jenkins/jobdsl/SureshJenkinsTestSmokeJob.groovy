import SureshJenkinsTestCompileJob
import alation.jobdsl.DslEnv
import alation.jobdsl.jobs.BuildJob

class SureshJenkinsTestSmokeJob extends BuildJob {

    // allow override of job name
    protected SureshJenkinsTestSmokeJob(jobName = BuildJob.jobSmoke) {
        super(jobName)
    }

    @Override
    protected void generateInstance() {
        super.generateInstance()

        this.environmentVariables.envs['VERSION_FILE'] = 'version.py'
        this.environmentVariables.envs['SHELL_DIR'] = '${WORKSPACE}/jenkins/shell'

        this.steps {
            shell('${SHELL_DIR}/SureshJenkinsTestSmokeJob.sh')
        }
    }
}
