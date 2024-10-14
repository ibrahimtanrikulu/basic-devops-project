resource "aws_vpc" "this" {
  cidr_block = var.vpc_cidr
  enable_dns_support   = true
  enable_dns_hostnames = true
  tags = {
    Name = "devops-project-vpc"
  }
}

resource "aws_subnet" "public" {
  count = length(var.public_subnet_cidrs)
  vpc_id     = aws_vpc.this.id
  cidr_block = var.public_subnet_cidrs[count.index]
  map_public_ip_on_launch = true
  availability_zone = var.public_subnet_zone
  tags = {
    Name = "devops-project-public-subnet"
  }
}

resource "aws_subnet" "private" {
  count = length(var.private_subnet_cidrs)
  vpc_id     = aws_vpc.this.id
  cidr_block = var.private_subnet_cidrs[count.index]
  availability_zone = var.private_subnet_zone
  tags = {
    Name = "devops-project-private-subnet"
  }
}

resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.this.id
  tags = {
    Name = "devops-project-internet-gateway"
  }
}

resource "aws_route_table" "public" {
  vpc_id = aws_vpc.this.id
  route {
    cidr_block = var.public_route_table_cidr
    gateway_id = aws_internet_gateway.igw.id
  }
  tags = {
    Name = "public-route-table"
  }
}
resource "aws_route_table" "private" {
  vpc_id = aws_vpc.this.id
  tags = {
    Name = "private-route-table"
  }
}

# Public Route Table Association (Public Subnet için)
resource "aws_route_table_association" "public_assoc" {
  count = length(var.public_subnet_cidrs)
  subnet_id      = aws_subnet.public[count.index].id
  route_table_id = aws_route_table.public.id
}
# Private Route Table Association (Private Subnet için)
resource "aws_route_table_association" "private_assoc" {
  count = length(var.private_subnet_cidrs)
  subnet_id      = aws_subnet.private[count.index].id
  route_table_id = aws_route_table.private.id
}