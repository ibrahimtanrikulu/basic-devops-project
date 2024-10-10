# Security Groups'tan gelen kaynakları çağır
module "security_groups" {
  source = "./security-groups"
  vpc_id = var.vpc_id
}

# Jenkins EC2 Instance
resource "aws_instance" "jenkins" {
  ami           = var.ami_id
  instance_type = var.instance_type
  subnet_id     = var.public_subnet_id
  security_groups = [aws_security_group.jenkins_sg.name]
  tags = {
    Name = "Jenkins-Server"
  }

  provisioner "local-exec" {
    command = "ansible-playbook -i ../../../ansible/inventory.ini ../../../ansible/playbook.yml --limit jenkins"
  }
}

# Nexus Server EC2 Instance
resource "aws_instance" "nexus" {
  ami           = var.ami_id
  instance_type = var.instance_type
  subnet_id     = var.public_subnet_id
  security_groups = [aws_security_group.nexus_sg.name]

  tags = {
    Name = "Nexus-Server"
  }

  provisioner "local-exec" {
    command = "ansible-playbook -i ../../../ansible/inventory.ini ../../../ansible/playbook.yml --limit nexus"
  }
}

# SonarQube EC2 Instance
resource "aws_instance" "sonarqube" {
  ami           = var.ami_id
  instance_type = var.instance_type
  subnet_id     = var.public_subnet_id
  security_groups = [aws_security_group.sonarqube_sg.name]

  tags = {
    Name = "SonarQube-Server"
  }

  provisioner "local-exec" {
    command = "ansible-playbook -i ../../../ansible/inventory.ini ../../../ansible/playbook.yml --limit sonarqube"
  }
} 

# helm_ec2 Instance Oluşturma
resource "aws_instance" "helm_ec2" {
  ami                    = var.ami_id
  instance_type          = var.instance_type
  key_name               = var.key_name
  vpc_security_group_ids = [module.security_group.this_security_group_id]
  subnet_id              = var.subnet_id

  provisioner "remote-exec" {
    inline = [
      "sudo apt-get update -y",
      "sudo apt-get install -y python3",
      "curl -s https://raw.githubusercontent.com/ansible/ansible/stable-2.9/contrib/inventory/ec2.py -o /etc/ansible/ec2.py",
      "chmod +x /etc/ansible/ec2.py"
    ]

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file(var.private_key_path)
      host        = self.public_ip
    }
  }

  tags = {
    Name = "Helm EC2"
  }
}