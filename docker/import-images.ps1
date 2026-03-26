Write-Host "开始导入镜像..."

docker load -i images/zhurong-platform-gateway.tar
docker load -i images/zhurong-platform-auth.tar
docker load -i images/zhurong-platform-core.tar
docker load -i images/zhurong-platform-custom-first.tar

Write-Host "镜像导入完成"