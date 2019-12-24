#!/bin/bash
#------------------------------------------------------------------------------
#
#  generate_entity_models.sh
#
#     generate entity models provided appropriate database connectivity
#     provide appropriate values in config/db_billpay.hibernate.properties and
#     config/db_billpay.reveng.xml
#------------------------------------------------------------------------------

export REVENG_XML_FILE=$(pwd -P)/config/db_billpay.reveng.xml
export REVENG_PROPERTY_FILE=$(pwd -P)/config/db_billpay.hibernate.properties
mvn hibernate3:hbm2java

# copy generated sources to src/main/java
mkdir -p src/main/java/hashpp/bills/model
cp -R target/generated-sources/hibernate3/hashpp/bills/model src/main/java/hashpp/bills/model