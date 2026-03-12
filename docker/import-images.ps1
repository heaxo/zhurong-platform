Write-Host "开始导入镜像..."

docker load -i images/ao-platform-gateway.tar
docker load -i images/ao-platform-auth.tar
docker load -i images/ao-platform-core.tar
docker load -i images/ao-platform-custom-first.tar

Write-Host "镜像导入完成"