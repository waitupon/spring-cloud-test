#!/usr/bin/env bash

set -eo pipefail

modules=(microserver-eureka-server microserver-eureka-client)

for module in "${modules[@]}"; do
     docker build -t "${module}" ${module}
done