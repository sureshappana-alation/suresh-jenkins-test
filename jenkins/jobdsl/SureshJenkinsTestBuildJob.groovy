import alation.jobdsl.jobs.BuildJob

// repeat steps form the JenkinsSmokeJob
// and extend with other steps
class SureshJenkinsTestBuildJob extends SureshJenkinsTestSmokeJob {

    protected SureshJenkinsTestBuildJob() {
        super(BuildJob.jobBuild)
    }

    @Override
    protected void generateInstance() {
        super.generateInstance()

        this.environmentVariables.envs['VERSION_FILE'] = 'version.py'
        this.environmentVariables.envs['SHELL_DIR'] = '${WORKSPACE}/jenkins/shell'

        this.steps {
            shell('${SHELL_DIR}/SureshJenkinsTestBuildJob.sh')
        }

        this.publishers.archiveArtifacts.with {
            patterns << 'version.py'
        }
    }
}
