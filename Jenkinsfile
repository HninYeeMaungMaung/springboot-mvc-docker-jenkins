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
                    // Optionally delete old pod (forces new one to pull updated image)
                    sh 'kubectl delete pod -l app=springboot-mvc-demo --ignore-not-found --grace-period=0 --force'
                    // Reapply the deployment YAML
                    sh 'kubectl apply -f k8s/deployment.yaml'
                }
            }
        }
    }
}
