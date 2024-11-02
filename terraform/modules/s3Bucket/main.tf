resource "aws_s3_bucket" "devops_old_version_app_bucket" {
  bucket = "devops-old-version-app-bucket"

  tags = {
    Name        = "oldVersionApp"
    Environment = "Dev"
  }
}

resource "aws_s3_bucket" "devops_solar_qube_old_version_bucket" {
  bucket = "devops-solar-qube-app-bucket"

  tags = {
    Name        = "oldVersionSolarQube"
    Environment = "Dev"
  }
} 
