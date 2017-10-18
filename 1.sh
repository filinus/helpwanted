java  -jar ./swagger-codegen-cli.jar generate \
  -i ./"swagger (21).yaml" \
  -c ./options.json \
  --group-id=us.filin.helpwanted \
  --artifact-id=project \
  --library jersey2 \
  -l jaxrs \
  -o ./gen3 \
  --model-name-suffix=Jsons 

  
