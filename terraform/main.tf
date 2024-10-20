provider "aws" {
  region = var.aws_region
}
# VPC modülü çağırılıyor
module "vpc" {
  source = "./modules/vpc"
}

module "security_groups" {
  source = "./modules/security-group"
  vpc_id = module.vpc.vpc_id
}
# IAM modülü çağırılıyor
module "iam" {
  source = "./modules/role"
}

# S3 modülü çağırılıyor
module "s3" {
  source = "./modules/s3Bucket"
}

# Jenkins modülünü çağır
module "jenkins" {
  source         = "./modules/ec2"
  security_group = module.security_groups.jenkins_sg_name
  subnet_id      = module.vpc.private_subnet_id
  iam_role       = module.iam.s3_role_name
}

module "nexus" {
  source         = "./modules/ec2"
  security_group = module.security_groups.nexus_sg_name
  subnet_id      = module.vpc.private_subnet_id
  iam_role       = module.iam.s3_role_name
}

module "sonarqube" {
  source         = "./modules/ec2"
  security_group = module.security_groups.sonarqube_sg_name
  subnet_id      = module.vpc.private_subnet_id
  iam_role       = module.iam.s3_role_name
}

module "helm_ec2" {
  source         = "./modules/ec2"
  security_group = module.security_groups.helm_sg_name
  subnet_id      = module.vpc.public_subnet_id
  iam_role       = module.iam.s3_role_name
}
