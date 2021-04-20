#!/bin/sh
curl -X POST --user 653D41R4LF93O5CJ6BBSH7BLR:IMdjNZG3U3uIlIWrI4kUJaJyZBixNzpDiHKBHnARtCs \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{
           "name" : "Bottler Liquor registry"
        }' \
    'https://api.stormpath.com/v1/applications?createDirectory=true'
