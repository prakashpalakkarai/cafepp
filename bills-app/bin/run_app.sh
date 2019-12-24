#!/bin/bash
#------------------------------------------------------------------------------
#
#  run_app.sh
#
#     run the application with appropriate configuration
#     usage: run from project root folder like below having configurtion files
#            under config folder under project root
#
#             bash bin/run_app.sh
#
#------------------------------------------------------------------------------

java -jar target/bills-app-0.0.1-SNAPSHOT.jar   \
          --spring.config.name=application,jdbc \
          --spring.config.location=file://$(pwd -P)/config/
