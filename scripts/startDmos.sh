#!/bin/bash

dmosHome=/home/mtilden/projects/dmos-backend-template
cwd=`pwd`
#set DB_ONLY to false if you want it to start up your backend app too and not just the DB
#you must also add dmos-std-out-err.log in your target directory for it to run the app
DB_ONLY=true
APP_ONLY=false

function waitForPostgresStart {

   startMsg="Checking that postgres is started"
   cmd="docker logs dmos_postgres 2>&1 | grep \"database system is ready to accept connections\" 2>&1 1>/dev/null"
   errorMsg="POSTGRES DID NOT START"

   waitForCommandToPass "${startMsg}" "${cmd}" "${errorMsg}"
}

function waitForDMOSToStart {
   startMsg="Checking that dmos is started"
   cmd="grep \"Started DmosBackendTemplateApplication in\" ${dmosHome}/target/dmos-std-out-err.log 2>&1 1>/dev/null"
   errorMsg="DMOS DID NOT START"

   waitForCommandToPass "${startMsg}" "${cmd}" "${errorMsg}"
}

function waitForCommandToPass {

   startMessage=$1
   cmd=$2
   errorMsg=$3

   let COUNTER=0
   echo ${startMessage}
   serviceStarted=false

   while [[  $COUNTER -lt 180 ]] && ! ${serviceStarted}; do
       let COUNTER=COUNTER+1
       eval ${cmd} 2>&1 1>/dev/null
       if [ $? -eq 0 ]; then
          serviceStarted=true
       else
          sleep 1
       fi
   done

   if ! ${serviceStarted}; then
     echo ${errorMsg}
     exit 1
   fi
}

function check_args(){
	while getopts "ad" option;
	do
		case "${option}"
		in
			d) DB_ONLY=true
			;;
			a) APP_ONLY=true
			;;
			\? )
			echo "Invalid option ${option}"
			;;
		esac
	done
}

function start_postgres(){

	if [ "$APP_ONLY" == true ]; then
		echo "Only starting the application. Not starting the DB";
		return 0;
	fi

	docker run --name dmos_postgres -e POSTGRES_DB=dmos -e POSTGRES_PASSWORD=squirrel1 -e POSTGRES_USER=postgres -p 5432:5432 --rm -d postgres
	waitForPostgresStart
	sleep 3
	docker exec -it dmos_postgres psql -Upostgres -a dmos -c 'create schema dmos_local'


	echo "Postgres up"
}

function start_keycloak(){

	if [ "$APP_ONLY" == true ]; then
		echo "Only starting the application. Not starting the DB";
		return 0;
	fi

	docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak

	echo "Keycloak up"
}

function start_portal(){
	if [ "$DB_ONLY" == true ]; then
		echo "Only starting the database.";
		return 0;
	fi

	cd $dmosHome
	rm -rf target/*.log
	mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=4444" -Dspring-boot.run.profiles=local -Dspring-boot.run.arguments=--logging.file.path=target/ 2>&1 1>target/dmos-std-out-err.log&
	waitForDMOSToStart

	echo "dmos started"

	cd $cwd
}

check_args $@
start_postgres
start_portal