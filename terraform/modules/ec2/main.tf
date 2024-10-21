# Jenkins EC2 Instance
resource "aws_instance" "jenkins" {
  ami             = var.ami_id
  instance_type   = var.instance_type
  subnet_id       = var.subnet_id
  security_groups = [var.security_group]
  key_name        = var.key_names[0]
  iam_instance_profile = var.iam_role
  tags = {
    Name = "Jenkins-Server"
  }

  provisioner "local-exec" {
    command = "ansible-playbook -i ../../../ansible/inventory.ini ../../../ansible/playbook.yml --limit jenkins"
  }
}

# # Nexus Server EC2 Instance
resource "aws_instance" "nexus" {
  ami             = var.ami_id
  instance_type   = var.instance_type
  subnet_id       = var.subnet_id
  security_groups = [var.security_group]
  key_name        = var.key_names[1]
  iam_instance_profile = var.iam_role
  tags = {
    Name = "Nexus-Server"
  }

  provisioner "local-exec" {
    command = "ansible-playbook -i ../../../ansible/inventory.ini ../../../ansible/playbook.yml --limit nexus"
  }
}

# # SonarQube EC2 Instance
resource "aws_instance" "sonarqube" {
  ami             = var.ami_id
  instance_type   = var.instance_type
  subnet_id       = var.subnet_id
  security_groups = [var.security_group]
  key_name        = var.key_names[2]
  iam_instance_profile = var.iam_role
  tags = {
    Name = "SonarQube-Server"
  }

  provisioner "local-exec" {
    command = "ansible-playbook -i ../../../ansible/inventory.ini ../../../ansible/playbook.yml --limit sonarqube"
  }
}

# # helm_ec2 Instance Oluşturma
resource "aws_instance" "helm_ec2" {
  ami                    = var.ami_id
  instance_type          = var.instance_type
  vpc_security_group_ids = [var.security_group]
  subnet_id              = var.subnet_id
  key_name               = var.key_names[3]
  iam_instance_profile = var.iam_role 
  provisioner "local-exec" {
    command = "ansible-playbook -i ../../../ansible/inventory.ini ../../../ansible/playbook.yml --limit helm"
  }

  tags = {
    Name = "Helm EC2"
  }
}
