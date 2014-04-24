#!/bin/sh

SCRIPT_DIR=`dirname $0`
ROOT_DIR=${SCRIPT_DIR}/..
VERSION=`cd ${ROOT_DIR} && bin/kune-version`

RELEASE_DIR=${ROOT_DIR}/release
WEBAPP_DIR=${RELEASE_DIR}/webapp

mkdir -p ${RELEASE_DIR}
mkdir -p ${WEBAPP_DIR}
mkdir -p ${WEBAPP_DIR}/WEB-INF

ln -s ../target/kune-${VERSION}-complete.jar ${RELEASE_DIR}/kune.jar

# Webapp files
for i in css object-embed-devel.html others static tutorials wse-dev.html wse.html ws.html
do
  ln -s ../../src/main/webapp/${i} ${WEBAPP_DIR}
done

for i in web.xml localhost.cer localhost.key
do
  ln -s ../../../src/main/webapp/WEB-INF/${i} ${WEBAPP_DIR}/WEB-INF
done

# GWT files
for i in ws wse
do
  ln -s ../../target/kune-${VERSION}/${i} ${WEBAPP_DIR}
done

cd ${RELEASE_DIR} && zip -r ../kune.zip *
