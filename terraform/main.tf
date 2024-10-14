provider "aws" {
  region = var.aws_region
}

# VPC modülü çağırılıyor
module "vpc" {
  source = "./modules/vpc"
  vpc_cidr = "10.0.0.0/16"
  public_subnet_cidrs = ["10.0.1.0/24"]
  private_subnet_cidrs = ["10.0.2.0/24"]
}