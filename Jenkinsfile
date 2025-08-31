pipeline {
  agent any

  environment {
    // Project workspace
    COMPOSE_PROJECT_DIR = "${WORKSPACE}"

    // DB Settings
    MYSQL_ROOT_PASSWORD = "admin123"
    MYSQL_DATABASE      = "expense_recorder"

    // App port exposed to your host
    APP_PORT            = "9090"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build JAR') {
      steps {
        sh 'mvn -B clean package -DskipTests'
        sh 'ls -l target | grep expense-app || true'
      }
    }

    stage('Docker Compose up') {
      steps {
        // Write .env file dynamically using Jenkins environment
        sh '''
          cat > .env <<EOF
MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
MYSQL_DATABASE=${MYSQL_DATABASE}
APP_PORT=${APP_PORT}
EOF
        '''
        // restart stack
        sh 'docker compose down || true'
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
