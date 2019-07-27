pipeline {
  agent any
  stages {
    stage('prepare-test') {
      when {
        branch 'test'
      }
      steps {
        git(url: env.gitUrl+'/'+env.projectName+'.git', branch: 'test', credentialsId: '6ed03dc02abc63041fb10f034f8434b93bec7cb0')
      }
    }
    stage('prepare-master') {
      when {
        branch 'master'
      }
      steps {
        git(url: env.gitUrl+'/'+env.projectName+'.git', branch: 'master', credentialsId: '6ed03dc02abc63041fb10f034f8434b93bec7cb0')
      }
    }
    stage('build-test') {
      when {
        branch 'test'
      }
      steps {
        sh 'gradle build publishMavenPublicationToNexusRepository -x Test'
        sh 'docker build -t ${dockerUrl}/${buildName}:${buildVersion}-test .'
        sh 'docker login -u admin -p 123456 ${dockerUrl}'
        sh 'docker push ${dockerUrl}/${buildName}:${buildVersion}-test'
        sh 'docker rmi ${dockerUrl}/${buildName}:${buildVersion}-test'
      }
    }
    stage('build-master') {
      when {
        branch 'master'
      }
      steps {
        sh 'gradle build publishMavenPublicationToNexusRepository -Penv=pro -x Test'
        sh 'docker build -t ${dockerUrl}/${buildName}:${buildVersion}-master .'
        sh 'docker login -u admin -p 123456 ${dockerUrl}'
        sh 'docker push ${dockerUrl}/${buildName}:${buildVersion}-master'
        sh 'docker rmi ${dockerUrl}/${buildName}:${buildVersion}-master'
      }
    }
  }
  environment {
    projectName = 'dubbo-controller-gradle'
    buildName = 'dubbo-controller'
    buildVersion='1.0.0'
    gitUrl = 'https://github.com/pengfanglin'
    dockerUrl='docker.qubaotang.cn'
  }
}