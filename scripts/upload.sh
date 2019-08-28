#!/usr/bin/env bash
curl -X POST -H "collection: collection1" -H "filename: data1.bin" --data-binary @data1.bin http://localhost:8081/collections &
curl -X POST -H "collection: collection1" -H "filename: data2.bin" --data-binary @data2.bin http://localhost:8081/collections &
curl -X POST -H "collection: collection2" -H "filename: data3.bin" --data-binary @data3.bin http://localhost:8081/collections
