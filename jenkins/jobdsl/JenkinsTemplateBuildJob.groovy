import alation.jobdsl.jobs.BuildJob

// repeat steps form the JenkinsSmokeJob
// and extend with other steps
class JenkinsTemplateBuildJob extends JenkinsTemplateSmokeJob {

    protected JenkinsTemplateBuildJob() {
        super(BuildJob.jobBuild)
    }

    @Override
    protected void generateInstance() {
        super.generateInstance()

        this.environmentVariables.envs['VERSION_FILE'] = 'version.py'
        this.environmentVariables.envs['SHELL_DIR'] = '${WORKSPACE}/jenkins/shell'

        this.steps {
            shell('${SHELL_DIR}/JenkinsTemplateBuildJob.sh')
        }

        this.publishers.archiveArtifacts.with {
            patterns << 'version.py'
        }
    }
}
