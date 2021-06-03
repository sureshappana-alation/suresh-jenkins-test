# Summary
This is a `Hello World` template to get a new project setup with Jenkins.

It may seem like a lot of steps, but going down this path is dangerous...

# How to use this repository
* `git clone` the repo
* Copy Files into your repo
  * `cp -r jenkins /path/to/your/repo`
* Create branch
  * `cd /path/to/your/repo/jenkins`
* Rename all scripts and Class Name to match your project.
  * `jobdsl/JenkinsTemplateBuildJob.groovy` - rename file and class name.
  * `jobdsl/JenkinsTemplateCompileJob.groovy` - rename file and class name
  * `jobdsl/JenkinsTemplateConfig.groovy` - rename file and binding name with repo name.
  * `jobdsl/JenkinsTemplateSmokeJob.groovy` - rename file and class name
  * `jobdsl/project_jobdsl.groovy` - use your class names
  * `jobdsl/seed_jobdsl.groovy`
* Create a private branch, push to Github, and open a PR
* Go to Jenkins and make sure the project has the correct layout.
  * repo_name
      * private
      * master
* Copy seed_subfolder from [here](https://jenkins.alationdata.com/view/All/job/alation_jenkins_template/job/private/job/seed_subfolder/) into the root of your private folder.
* Run the build and see if it's green, if it's not green, fix any errors.
* Go to your master folder
* copy over the seed job from [here](https://jenkins.alationdata.com/view/All/job/alation_jenkins_template/job/master/job/seed/)
* Run the seed job from the master folder, put your private branch `GIT_BUILD_BRANCH_PARAM`
* You will now have all github hooks enabled on your repo. You can enforce branch protection and status checks now.
* These will fail, as your changes aren't in master yet for DSL support.
* Once, landed, extend as needed.

# Changing


# How this repo was built
* Install Java - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* Install Gradle `brew install gradle`
* `vim version.py`

```
MAJOR = 1
MINOR = 0
PATCH = 0
```

* `mkdir jenkins && cd jenkins`
* `gradle init --type java-application`
  * Select groovy application
  * Select any testing framework
  * Project Name: jenkins
  * Project Source: jenkins
* Edit the `jenkins/gradle/wrapper/gradle-wrapper.properties` file.

```
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
distributionUrl=https\://artifactory.alationdevops.com/artifactory/gradle-local/gradle/2.14/gradle-2.14-all.zip
```

* Edit the `build.gradle` file.

```groovy
apply plugin: 'groovy'

configurations {
    localDependencies
    localDependencies.transitive = false
}

repositories {
    maven { url "https://artifactory.alationdevops.com/artifactory/jenkins-core" }
}

dependencies {
    compile 'org.codehaus.groovy:groovy-all:2.4.7'
    localDependencies "${alationJobDSLGroup}:${alationJobDSLProject}:${alationJobDSLVersion}"
    compile configurations.localDependencies
}

sourceSets {
    main {
        groovy {
            srcDirs = ['jobdsl']
        }
    }
}

task setupSeedClasspath(dependsOn: 'setupSeedResources', type: Copy) {
    from configurations.localDependencies
    into "${project.projectDir}/../libs/jobdsl/"
}

task setupSeedResources(type: Copy){
    from zipTree(configurations.localDependencies.singleFile)
    // Add the path to include files which are necessary for the jobs that will be seeded
    // Chef Repositories need files in chef/cookbook and JiraPublisher job needs changelog.txt
    include('changelog.txt','chef/cookbook/**','alation/jobdsl/scripts/\*\*')
    into file("${project.projectDir}/../libs/jobdsl/")
}

task wrapper(type: Wrapper) {
    gradleVersion = '2.14'
}
```

* Create another `gradle.properties` file.

```
# Specify the artifact organization name defaults to "main"
# Change to "test" for artifacts built on https://jenkins.alation.us
alationJobDSLGroup=main
# Specify the jobDSL project name
# "alation-jobdsl-core" for dependency built on master
# "alation-jobdsl-private" for dependency built on private branch
alationJobDSLProject=alation-jobdsl-core
# Specify either concrete version a.b.c.d for exact version or a.b.c.+ to use latest version
alationJobDSLVersion=5.0.0.+
```

* `mkdir jobdsl && mkdir shell && cd jobdsl`
* Next we must create our basic Jenkins files
  * XYZConfig.groovy - This is where we set the git repo name
  * XYZCompileJob.groozy - this is
  * XYZBuildJob.groovy -
  * XZYSmokeJob.groovy
  * project_jobdsl.groovy
  * seed_job.groovy
