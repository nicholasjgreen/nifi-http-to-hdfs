# Nifi HTTP to HDFS
This is a working example of how to accept HTTP POST messages in Nifi and deliver them to HDFS.


## Setting up
1. Bring up the stack with docker compose.
    ```bash
    docker-compose up -d
    ```
1. Run the config-nifi script 
    ```bash
    docker-compose exec nifi bash /app/scripts/config_nifi.sh
    ```
1. Use the Nifi web UI to create the workflow
    1. Navigate your browser to http://localhost:8080/nifi
    1. Right click on the canvas, select "Upload Template"
    1. Select the file nifi_templates/HTTP_to_HDFS.xml and click Upload
    1. UI will confirm that the template was successfully imported. Click OK
    1. Drag the "Add Template" control (from the tool bar at the top) onto the canvas and release
    1. Select HTTP to HDFS, click Add
    1. On each component, right click and select "Start"
1. Upload some files
    ```bash
    ./scripts/create_files.sh
    ./scripts/upload.sh
    ```
1. Check the content of hdfs
    ```bash
    docker-compose exec hadoop bash /app/scripts/whats_on_hdfs.sh
    ```

You should see results something like this
```bash
Found 1 items
drwxr-xr-x   - root supergroup          0 2019-07-29 08:30 /collections
Found 2 items
drwxr-xr-x   - root supergroup          0 2019-07-29 08:31 /collections/collection1
drwxr-xr-x   - root supergroup          0 2019-07-29 08:30 /collections/collection2
Found 2 items
-rw-r--r--   3 root supergroup  104857600 2019-07-29 08:30 /collections/collection1/72833668030983
-rw-r--r--   3 root supergroup  104857600 2019-07-29 08:31 /collections/collection1/72833668149083
Found 1 items
-rw-r--r--   3 root supergroup  104857600 2019-07-29 08:30 /collections/collection2/72833668091783
```
    
