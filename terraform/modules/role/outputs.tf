output "ec2_role_name" {
  description = "Name of the EC2 IAM Role"
  value       = aws_iam_role.ec2_role.name
} 