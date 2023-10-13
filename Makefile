build:
	sudo mvn clean package

run:
	sudo docker-compose up

stop:
	docker-compose down

test:
	mvn test
