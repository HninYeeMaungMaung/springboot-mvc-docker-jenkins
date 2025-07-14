pipeline {
    agent any

    environment {
        IMAGE_NAME = "springboot-mvc-demo:latest"
        PATH = "/opt/homebrew/opt/maven/bin:/opt/homebrew/bin:/usr/local/bin:$PATH"
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'eval $(minikube docker-env)'
                    sh 'docker build --no-cache -t springboot-mvc-demo:latest .'
                }
            }
        }

        stage('Deploy to Minikube') {
            steps {
                script {
                    sh 'kubectl delete pod -l app=springboot-mvc-demo --grace-period=0 --force || true'
                    sh 'kubectl apply -f k8s/deployment.yaml'
                    sh 'kubectl apply -f k8s/service.yaml'
                }
            }
        }

        stage('Apply HPA') {
             steps {
                 script {
                     sh 'kubectl apply -f k8s/hpa.yaml'
                 }
             }
         }

        stage('Monitor Status') {
            steps {
                sh 'kubectl get pods'
                sh 'kubectl get hpa'
            }
        }
    }
}
