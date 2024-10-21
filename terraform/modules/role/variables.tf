variable "ec2_role_name" {
  description = "The name of the EC2 IAM role"
  type        = string
  default     = "ec2_role"
} 
variable "ec2_policy_name" {
  description = "The name of the EC2 IAM policy"
  type        = string
  default     = "ec2_policy"
} 
