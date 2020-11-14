pipeline {
    agent any

    stages {

        stage('pull code'){
          steps{
            checkout([$class: 'GitSCM',
            branches: [[name: '*/master']],
            doGenerateSubmoduleConfigurations: false,
            extensions: [],
             submoduleCfg: [],
             userRemoteConfigs: [[credentialsId: 'f58d6144-0197-446a-835f-3fbfcda0d8cb', url: 'https://github.com/hello-work/demo.git']]])
          }
        }

        stage('build project'){
          steps{
            sh 'mvn clean package'
          }
        }

        stage('pulish project'){
          steps{
            echo 'pulish project'
          }
        }

    }
}