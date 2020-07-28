import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.1"

project {

    vcsRoot(Repo2)
    vcsRoot(id67021vcs)

    buildType(id67021)
}

object id67021 : BuildType({
    id("67021")
    name = "67021"

    vcs {
        root(id67021vcs)
        root(Repo2, "+:. => .repo2")
    }

    steps {
        powerShell {
            name = "step1_change"
            scriptMode = file {
                path = "projectfile1.ps1"
            }
            noProfile = false
        }
        powerShell {
            name = "step2"
            scriptMode = file {
                path = ".repo2/repo2.ps1"
            }
        }
    }
})

object id67021vcs : GitVcsRoot({
    id("67021vcs")
    name = "67021vcs"
    url = "https://github.com/AChubatova/67021"
})

object Repo2 : GitVcsRoot({
    name = "repo2"
    url = "https://github.com/AChubatova/67021-2"
    authMethod = password {
        userName = "AChubatova"
        password = "zxx420e17ae4da894816f2da6042c153379"
    }
})
