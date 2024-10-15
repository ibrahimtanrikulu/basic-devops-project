resource "aws_s3_bucket" "app_old_version_bucket" {
  bucket = "app-test-bucket"
  acl    = "private"

  versioning {
    enabled = true
  }

  tags = {
    Name        = "oldVersionApp"
    Environment = "Dev"
  }
}

resource "aws_s3_bucket" "old_version_solar_qube_bucket" {
  bucket = "app-solar-qube-bucket"
  acl    = "private"

  versioning {
    enabled = true
  }

  tags = {
    Name        = "oldVersionSolarQube"
    Environment = "Dev"
  }
}