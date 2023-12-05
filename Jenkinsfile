node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "./gradlew sonar \
            -Dsonar.projectKey=Kweku-A_ScrumExams_AYw6BGArqsL00adsOdul \
            -Dsonar.projectName='ScrumExams' \
            -Dsonar.host.url=http://localhost:9000"
    }
  }
}