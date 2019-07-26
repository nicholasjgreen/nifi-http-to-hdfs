#!/usr/bin/env bash
curl -X POST -H "collection: collection1" --data-binary @data1.bin http://localhost:8081/collections &
curl -X POST -H "collection: collection1" --data-binary @data2.bin http://localhost:8081/collections &
curl -X POST -H "collection: collection2" --data-binary @data3.bin http://localhost:8081/collections
