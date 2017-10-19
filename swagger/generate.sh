#!/usr/bin/env bash
SCRIPTDIR=`dirname $0`
PROJECTDIR=$SCRIPTDIR/../
CODEGENDIR=$PROJECTDIR/../

java  -jar $CODEGENDIR/swagger-codegen-cli.jar generate \
  -i $SCRIPTDIR/"swagger (22).yaml" \
  -c $SCRIPTDIR/options.json \
  --group-id=us.filin.helpwanted \
  --artifact-id=project \
  --library jersey2 \
  -l jaxrs \
  -o $PROJECTDIR \
  --model-name-suffix=POJO

  
