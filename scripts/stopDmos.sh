#!/bin/bash

APP_ONLY=false
function check_args(){
	while getopts "a" option;
	do
		case "${option}"
		in
			a) APP_ONLY=true
			;;
			\? )
			echo "Invalid option ${option}"
			;;
		esac
	done
}

function stop_app(){

	ps -ef | grep java | grep -i "dmos\..*Application"   | awk '{print $2}' | xargs kill -9
	ps -ef | grep java | grep plexus-classworlds | awk '{print $2}' | xargs kill -9
	echo "Checking that services stopped"
	allServicesStopped=false
	while [[  $COUNTER -lt 50 ]] && ! $allServicesStopped; do
		let COUNTER=COUNTER+1
		ps -ef | grep java | grep -i "dmos\..*Application"
		if [ ! $? -eq 0 ]; then
		   allServicesStopped=true
		fi
	done

	if ! $allServicesStopped; then
	  echo "Services did not stop"
	fi

}

function stop_postgres(){
	if [ "$APP_ONLY" == true ]; then
		echo "Only stopping the application.";
		return 0;
	fi
	docker rm -f dmos_postgres
}

function stop_keycloak(){
	if [ "$APP_ONLY" == true ]; then
		echo "Only stopping the application.";
		return 0;
	fi
	docker rm -f quay.io/keycloak/keycloak
}

check_args $@
stop_app
stop_keycloak
stop_postgres