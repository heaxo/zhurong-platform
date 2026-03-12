#!/bin/bash

mkdir -p publish/images

docker save -o publish/images/ao-platform-gateway.tar a.he/ao-platform-gateway
docker save -o publish/images/ao-platform-auth.tar a.he/ao-platform-auth
docker save -o publish/images/ao-platform-core.tar a.he/ao-platform-core
docker save -o publish/images/ao-platform-custom-first.tar a.he/ao-platform-custom-first

echo "镜像导出完成"