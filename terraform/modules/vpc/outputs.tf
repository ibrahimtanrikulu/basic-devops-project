output "vpc_id" {
  value = aws_vpc.this.id
}

output "public_route_table_id" {
  value = aws_route_table.public.id
}

output "private_route_table_id" {
  value = aws_route_table.private.id
}

output "internet_gateway_id" {
  value = aws_internet_gateway.igw.id
}

output "public_subnet_id" {
  value = aws_subnet.public[0].id  # İlk public subnet'in ID'sini al
}

output "private_subnet_id" {
  value = aws_subnet.private[0].id  # İlk private subnet'in ID'sini al
}
