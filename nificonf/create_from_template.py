import nipyapi
import time
from retrying import retry


@retry(wait_fixed=15000)
def connect_to_nifi():
    nipyapi.canvas.get_root_pg_id()


nipyapi.config.nifi_config.host = 'http://localhost:8080/nifi-api'
nipyapi.config.registry_config.host = 'http://localhost:8081/nifi-registry-api'
print("Connecting to nifi...")
connect_to_nifi()

#for attempt in range(100):
#    try:
#    except:
#        print("Sleeping 15 seconds (nifi can take a long time to come up...)")
#        time.sleep(15)
#    else:
#        break

rootPg = nipyapi.canvas.get_root_pg_id()
print("Root process group =", rootPg)
response = nipyapi.templates.upload_template(rootPg, "./nifi_templates/HTTP_to_HDFS.xml")
templateId = response.template.id

#templates = nipyapi.templates.list_all_templates()
#print("Got", len(templates.templates), "templates")
#for template in templates.templates:
#    print(template)

nipyapi.templates.deploy_template(rootPg, templateId)
nipyapi.canvas.schedule_process_group(rootPg, True)
