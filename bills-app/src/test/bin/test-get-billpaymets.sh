#!/bin/bash
#------------------------------------------------------------------------
#
# script: test-get-billpayments.sh
#
# test billpayments api for get and post
# 
# usage: test-get-billpayments.sh <<generated password>>
#
#
#------------------------------------------------------------------------

echo -e "Get All Billpayments...\n"
curl -v -u user:$1 http://localhost:8080/api/billpayments/

echo -e "\n\nGet Billpayment for 1...\n"
curl -v -u user:$1 http://localhost:8080/api/billpayments/1

echo -e "\n\nPost Billpayment for 1...\n"

base64_auth=$(echo -n "user:$1" | base64)

curl -v -X POST \
  http://localhost:8080/api/billpayments/ \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Content-Type: application/json' \
  -H "Authorization: Basic $base64_auth" \
  -H 'Host: localhost:8080' \
  -d @bpreq.json

