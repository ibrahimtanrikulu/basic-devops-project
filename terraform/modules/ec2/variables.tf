variable "key_names" {
  type    = list(string)
  default = ["key1", "key2", "key3", "key4", "key5"]
} 

variable "public_subnet_id" {
  description = "The ID of the public subnet where EC2 instances will be launched."
  type        = string
} 

variable "instance_type" {
  description = "The type of EC2 instance to be created."
  type        = string
  default     = "t2.micro"  # Varsayılan olarak t2.micro kullanılır.
}

variable "ami_id" {
  description = "The AMI ID to use for the EC2 instances."
  type        = string
  default     = "ami-0c55b159cbfafe1f0"  # Varsayılan olarak Ubuntu 20.04 AMI
} 

variable "subnet_id" {
  description = "VPC Subnet ID"
  type        = string
}