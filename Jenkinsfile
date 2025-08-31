pipeline {
  agent any

  environment {
    // make sure .env exists or set secrets here via Jenkins credentials bindings
    // The .env in repo or created on EC2 will be used by docker compose
    COMPOSE_PROJECT_DIR = "${WORKSPACE}"
  }

  stages {
    stage('Checkout') {
      steps {
        // Use the Git SCM configured for your job; if using credentials, set them in job config
        checkout scm
      }
    }

    stage('Build JAR') {
      steps {
        // run maven build; skip tests in pipeline run if you want, or run tests
        sh 'mvn -B clean package -DskipTests'
        // verify jar file
        sh 'ls -l target | grep expense-app || true'
      }
    }

    stage('Docker Compose up') {
      steps {
        // ensure previous containers removed (optional)
        sh 'docker compose down || true'
        // build images and start containers detached
        sh 'docker compose up --build -d'
      }
    }
  }

  post {
    success {
      echo "Pipeline succeeded"
    }
    failure {
      echo "Pipeline failed"
      sh 'docker compose logs --no-color || true'
    }
    always {
      archiveArtifacts artifacts: 'target/expense-app.jar', allowEmptyArchive: true
    }
  }
}
