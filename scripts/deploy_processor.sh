#!/bin/bash
# Stop nifi to make sure nothing goes weird
/nifi/bin/nifi.sh stop

# Install sig check processor
cp /app/nifi_sig_check/nifi-nifi_sig_check-nar/target/*.nar /nifi/lib/

# Restart nifi to make sure the nar has been included
/nifi/bin/nifi.sh start

# Wait a bit and then get the status
sleep 3
/nifi/bin/nifi.sh status
