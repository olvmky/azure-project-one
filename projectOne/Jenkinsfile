pipeline {

  agent {
    kubernetes {
    cloud 'kubernetes'
    label 'reimb-pod'
    idleMinutes 5
    yamlFile 'build-pod.yaml'
    defaultContainer 'maven'
    }
  }

  stages {
    stage('Test') {
      steps {
        sh 'mvn -f pom.xml test'
      }
    }

  stage('Build') {
            steps {
                sh 'mvn -f pom.xml clean install'
                sh 'mvn -f pom.xml clean package -DskipTests'
            }
   }

   stage('Docker Build') {
     steps {
       container('docker') {
          script {
            sh "docker build -t olvmky/reimb-api ."
          }
        }
     }
   }

   stage('Docker Deliver') {
        steps {
          container('docker') {
            script {
              sh "docker login -u olvmky -p a643fb0e-06a3-478c-8cc7-3d4e32ba3fa0"
              sh "docker push olvmky/reimb-api:latest"
            }
          }
        }
    }

    stage('Canary Deployment') {
         steps {
           withKubeConfig([credentialsId: 'cluster-test',serverUrl: 'https://34.66.168.93',
                                caCertificate: '', clusterName:'jenkins']) {
             sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'
             sh 'chmod u+x ./kubectl'
             sh './kubectl apply -f deployment.yml'
             sh './kubectl apply -f deployment-canary.yml'
//              sh 'curl -ks https://`./kubectl get svc reimb-api-service -o=jsonpath="{.status.loadBalancer.ingress[0].ip}"`/version'
//              sh 'while true; do curl -ks https://`./kubectl get svc reimb-api-service -o=jsonpath="{.status.loadBalancer.ingress[0].ip}"`/version; sleep 1; done'
//              sh 'export BACKEND_SERVICE_IP=$(./kubectl get -o jsonpath="{.status.loadBalancer.ingress[0].ip}" svc reimb-api-service) while true; do curl http://BACKEND_SERVICE_IP/version; sleep 3;  done'
           }

           cleanWs()

//                 discordSend description: "Build #$currentBuild.number",
//                   link: BUILD_URL, result: currentBuild.currentResult,
//                   title: JOB_NAME,
//                   webhookURL: "https://discord.com/api/webhooks/946097550514061343/7IRGxvAsw24cbGPIHXE15gtxCvzQQtRl3e5DEcm7arQpC6x3cVJPXXWZo7UWHKyJumuW"
              }
      }
  }
}
