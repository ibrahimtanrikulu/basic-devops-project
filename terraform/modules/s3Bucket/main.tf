resource "aws_s3_bucket" "app_last_version_bucket" {
  bucket = "app-test-bucket"
  acl    = "private"

  versioning {
    enabled = true
  }

  tags = {
    Name        = "lastVersionApp"
    Environment = "Dev"
  }
}