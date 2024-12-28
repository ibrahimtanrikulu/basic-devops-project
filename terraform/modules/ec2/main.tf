# Jenkins EC2 Instance
resource "aws_instance" "jenkins" {
  ami                         = var.ami_id
  instance_type               = var.instance_type
  subnet_id                   = var.subnet_id
  security_groups             = [var.security_group]
  key_name                    = var.key_names[0]
  iam_instance_profile        = var.iam_role
  associate_public_ip_address = true
  tags = {
    Name = "Jenkins-Server"
  }
}

# # Nexus Server EC2 Instance
resource "aws_instance" "nexus" {
  ami                         = var.ami_id
  instance_type               = var.instance_type
  subnet_id                   = var.subnet_id
  security_groups             = [var.security_group]
  key_name                    = var.key_names[1]
  iam_instance_profile        = var.iam_role
  associate_public_ip_address = true
  tags = {
    Name = "Nexus-Server"
  }
}

# # SonarQube EC2 Instance
resource "aws_instance" "sonarqube" {
  ami                         = var.ami_id
  instance_type               = var.instance_type
  subnet_id                   = var.subnet_id
  security_groups             = [var.security_group]
  key_name                    = var.key_names[2]
  iam_instance_profile        = var.iam_role
  associate_public_ip_address = true
  tags = {
    Name = "SonarQube-Server"
  }
}

# # helm_ec2 Instance Oluşturma
resource "aws_instance" "helm_ec2" {
  ami                         = var.ami_id
  instance_type               = var.instance_type
  vpc_security_group_ids      = [var.security_group]
  subnet_id                   = var.subnet_id
  key_name                    = var.key_names[3]
  iam_instance_profile        = var.iam_role
  associate_public_ip_address = true

  tags = {
    Name = "Helm EC2"
  }
}
