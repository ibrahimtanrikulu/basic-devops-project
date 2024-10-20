output "jenkins_public_ip" {
  description = "The public IP address of the Jenkins EC2 instance."
  value       = aws_instance.jenkins.public_ip
}

output "nexus_public_ip" {
  description = "The public IP address of the Nexus EC2 instance."
  value       = aws_instance.nexus.public_ip
}

output "sonarqube_public_ip" {
  description = "The public IP address of the SonarQube EC2 instance."
  value       = aws_instance.sonarqube.public_ip
}

output "helm_public_ip" {
  description = "The public IP address of the SonarQube EC2 instance."
  value       = aws_instance.helm_ec2.public_ip
}