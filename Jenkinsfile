pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "your-dockerhub-username/my-app:${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'github-creds', url: 'https://github.com/your-username/my-app.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests' // generates target/myapp.jar
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                      docker build -t $DOCKER_IMAGE .
                      echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                      docker push $DOCKER_IMAGE
                    """
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withKubeConfig([credentialsId: 'kubeconfig-id']) {
                    sh """
                      kubectl set image deployment/my-app app=$DOCKER_IMAGE --namespace=default
                      kubectl rollout status deployment/my-app --namespace=default
                    """
                }
            }
        }
    }
}
