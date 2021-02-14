pipeline {
environment {
        REGISTRY = 'wellitonpaiva/mimimizer'
        REGISTRY_CREDENTIAL = 'docker-credential'
        NAME = "mimimizer"
    }
    agent {
        kubernetes {
            defaultContainer 'jnlp'
            yamlFile 'build.yaml'
        }
    }
    stages {
        stage('Test') {
            steps {
                container('gradle') {
                    sh 'gradle test'
                }
            }
        }
        stage('Build') {
            steps {
                container('gradle') {
                    sh 'gradle bootBuildImage --imageName=mimimizer'
                }
            }
        }
        stage('Docker Publish') {
            steps {
                container('docker') {
                    withDockerRegistry([credentialsId: "${REGISTRY_CREDENTIAL}", url: ""]) {
                        sh "docker push ${REGISTRY}:latest"
                    }
                }
            }
        }
        stage('Kubernetes Deploy') {
            when {
                environment name: 'DEPLOY', value: 'true'
            }
            steps {
                container('helm') {
                    sh "helm upgrade --install --force --set name=${REGISTRY} --set image.tag=latest ${NAME} ./helm"
                }
            }
        }
    }
}