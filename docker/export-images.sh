#!/bin/bash

mkdir -p publish/images

docker save -o publish/images/zhurong-platform-gateway.tar a.he/zhurong-platform-gateway
docker save -o publish/images/zhurong-platform-auth.tar a.he/zhurong-platform-auth
docker save -o publish/images/zhurong-platform-core.tar a.he/zhurong-platform-core
docker save -o publish/images/zhurong-platform-custom-first.tar a.he/zhurong-platform-custom-first

echo "镜像导出完成"