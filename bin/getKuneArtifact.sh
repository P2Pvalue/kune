#!/bin/sh

SERVER=http://grasia.fdi.ucm.es/p2pvalue/artifactory
REPO=p2pvalue-snapshot
GROUP=cc/kune

DIR=`dirname $0`
VERSION=`cd $DIR/.. && bin/kune-version`

VERSION_PATH="$SERVER/$REPO/$GROUP/kune-$VERSION-complete/$VERSION"

MAVEN_XML="maven-metadata.xml"
MAVEN_PATH="$VERSION_PATH/$MAVEN_XML"

LATEST=`curl -s $MAVEN_PATH | grep -m 1 '<value>' | sed "s/.*<value>\([^<]*\)<\/value>.*/\1/"`
wget -P "$DIR/../target/" "$VERSION_PATH/kune-$VERSION-complete-$LATEST.jar"
