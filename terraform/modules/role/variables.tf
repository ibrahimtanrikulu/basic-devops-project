variable "ec2_role_name" {
  description = "The name of the EC2 IAM role"
  type        = string
  default     = "ec2_role"
}

variable "s3_role_name" {
  description = "The name of the S3 IAM role"
  type        = string
  default     = "s3_role"
}

variable "ec2_policy_name" {
  description = "The name of the EC2 IAM policy"
  type        = string
  default     = "ec2_policy"
}

variable "s3_policy_name" {
  description = "The name of the S3 IAM policy"
  type        = string
  default     = "s3_policy"
}
