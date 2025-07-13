pipeline {
  agent any

  environment {
     PATH = "/opt/homebrew/opt/maven/bin:/opt/homebrew/bin:/usr/local/bin:$PATH"
  }

  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }
    stage('Docker Build') {
      steps {
        sh '''
          eval $(minikube docker-env)
          docker build -t springboot-mvc-demo .
        '''
      }
    }
    stage('Deploy to Minikube') {
      steps {
        sh 'kubectl apply -f k8s/deployment.yaml'
      }
    }
  }
}
