provider "aws" {
  region = var.aws_region
}

# IAM modülü çağırılıyor
module "iam" {
  source = "./modules/iam"
}

# S3 modülü çağırılıyor
module "s3" {
  source = "./modules/s3"
}

# VPC modülü çağırılıyor
module "vpc" {
  source = "./modules/vpc"
} 

