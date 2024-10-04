def call() {
    // Define SonarQube Scanner environment and tool
    def scannerHome = tool 'Sonar' // Replace 'SonarQubeScanner' with the correct tool name configured in Jenkins

    withSonarQubeEnv('Sonar') { // 'SonarQube' refers to the name of your SonarQube server configuration in Jenkins
        // Run SonarQube analysis with SCM disabled
        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=wanderlust -Dsonar.scm.disabled=true"

        // Wait for Quality Gate with timeout
        timeout(time: 5, unit: "MINUTES") {
            waitForQualityGate abortPipeline: false
        }
    }
}
