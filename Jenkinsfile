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
          sh 'eval $(minikube docker-env)'
          sh 'docker build --no-cache -t springboot-mvc-demo:latest .'
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
