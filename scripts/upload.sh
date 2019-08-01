#!/usr/bin/env bash
data1_md5=$(md5 -q data1.bin)
data2_md5=$(md5 -q data2.bin)
data3_md5=$(md5 -q data3.bin)

curl -X POST -H "collection: collection1" -H "Content-MD5: $data1_md5" --data-binary @data1.bin http://localhost:8081/collections &
curl -X POST -H "collection: collection2" -H "Content-MD5: $data2_md5" --data-binary @data2.bin http://localhost:8081/collections &
curl -X POST -H "collection: collection3" -H "Content-MD5: $data3_md5" --data-binary @data3.bin http://localhost:8081/collections
