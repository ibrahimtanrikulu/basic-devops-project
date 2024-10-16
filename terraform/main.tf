provider "aws" {
  region = var.aws_region
}

# IAM modülü çağırılıyor
module "iam" {
  source = "./modules/role"
}

# S3 modülü çağırılıyor
module "s3" {
  source = "./modules/s3Bucket"
}

# VPC modülü çağırılıyor
module "vpc" {
  source = "./modules/vpc"
} 

