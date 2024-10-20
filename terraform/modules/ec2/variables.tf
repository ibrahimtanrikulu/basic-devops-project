variable "key_names" {
  type    = list(string)
  default = ["key1", "key2", "key3", "key4"]
} 

variable "subnet_id" {
  description = "The ID of the public subnet where EC2 instances will be launched."
  type        = string
} 

variable "instance_type" {
  description = "The type of EC2 instance to be created."
  type        = string
  default     = "t2.micro"
}

variable "ami_id" {
  description = "The AMI ID to use for the EC2 instances."
  type        = string
  default     = "ami-0c55b159cbfafe1f0" 
}  

variable "security_group" {
  description = "A list of security group names to associate with the EC2 instance."
  type        = string
}

variable "iam_role" {
  description = "Iam role for EC2 instance."
  type        = string
}