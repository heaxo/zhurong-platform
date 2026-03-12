Write-Host "========== 部署开始 =========="

Write-Host "1. 导入镜像..."
.\import-images.ps1

Write-Host "2. 启动服务..."
docker compose up -d

Write-Host "3. 查看服务状态..."
docker compose ps

Write-Host "========== 部署完成 =========="