import alation.jobdsl.Downstream
import alation.jobdsl.EnvVar
import alation.jobdsl.Label
import alation.jobdsl.jobs.ChildJob

class JenkinsTemplateCompileJob extends ChildJob {

    // This compile job can be extended to have a test subType
    protected JenkinsTemplateCompileJob(compileFlavour) {
        super('compile', [compileFlavour])
        this.labels = [
                'uname:' + compileFlavour,
                Label.tenancyExclusive
        ]
    }

    @Override
    protected void generateInstance() {
        super.generateInstance()

        this.steps {
            shell('echo ' + Downstream.triggeringJobName + ': ' + EnvVar.formatVar(Downstream.triggeringJobName))
        }

        this.publishers.archiveArtifacts.with {
            patterns << 'version.py'
        }
    }
}
