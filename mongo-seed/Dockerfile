FROM mongo:latest

COPY post.json /post.json

CMD mongoimport --host mongo --db nonblockingapi --collection post --drop --file /post.json --jsonArray