#!/bin/bash
cp /app/conf/core-site.xml /nifi/conf/

# Install sig check processor
cp /app/nifi_sig_check/nifi-nifi_sig_check-nar/target/*.nar /nifi/lib/

# Restart nifi to make sure the nar has been included
/nifi/bin/nifi.sh restart
