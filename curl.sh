curl localhost:8080/api/v1/hello-world

curl localhost:8080/api/v1/orders

curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"user":"Sultan","restaurant":"Meme Curry",	"items": ["2x Meme Monster", "1x Tempura Appetizer"]}' \
  http://localhost:8080/api/v1/orders

