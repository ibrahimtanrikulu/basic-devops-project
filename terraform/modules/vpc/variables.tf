variable "vpc_cidr" {
    type = string
    default = "10.0.0.0/16"
}

variable "public_subnet_cidrs" {
    type = list(string)
    default = ["10.0.5.0/24","10.0.6.0/24","10.0.7.0/24"]
    description = "Public subnet"
}

variable "public_subnet_zone" {
    type = string
    default = "us-east-1a"
    description = "public zone"
}

variable "private_subnet_cidrs" {
    type = list(string)
    default = ["10.0.1.0/24","10.0.2.0/24","10.0.3.0/24"]
    description = "private subnet"
} 

variable "private_subnet_zone" {
    type = string
    default = "us-east-1a"
    description = "private zone"
}

variable "azs_cidr" {
    type = list(string)
    description = "Availability Zones"
    default = [ "us-east-1"] 
}


variable "public_route_table_cidr" {
    type = string
    default = "0.0.0.0/0"
    description = "public route table cidr block"
} 