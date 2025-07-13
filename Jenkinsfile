pipeline {
  agent any

  environment {
     PATH = "/opt/homebrew/Cellar/maven/3.9.10/libexec"
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
