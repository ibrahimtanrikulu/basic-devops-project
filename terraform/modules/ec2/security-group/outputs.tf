# Security Group isimlerini dışa aktar
output "jenkins_sg_name" {
  value = aws_security_group.jenkins_sg.name
}

output "nexus_sg_name" {
  value = aws_security_group.nexus_sg.name
}

output "sonarqube_sg_name" {
  value = aws_security_group.sonarqube_sg.name
}

output "helm_ec2_public_ip" {
  description = "Public IP of EC2 instance"
  value       = aws_instance.helm_ec2.public_ip
}