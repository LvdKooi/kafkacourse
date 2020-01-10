ga naar de kafka directory

start zookeeper

zookeeper-server-start config/zookeeper.properties

start kafka

kafka-server-start config/server.properties

kafka-topic aanmaken

kafka-topics --zookeeper 127.0.0.1:2181 --topic twitter --create --partitions 3 --replication-factor 1

kafka-topics bekijken

kafka-topics --zookeeper 127.0.0.1:2181 --list

specifieke kafka-topic bekijken

kafka-topics --zookeeper 127.0.0.1:2181 --topic twitter --describe

kafka-topic markeren voor verwijdering

kafka-topics --zookeeper 127.0.0.1:2181 --topic twitter --delete

kafka-producer cli aanmaken
kafka-console-producer --broker-list 127.0.0.1:9092 --topic twitter

kafka-producer cli met acknowledgment aanmaken (deze geeft terugkoppeling als het bericht ontvangen is)

kafka-console-producer --broker-list 127.0.0.1:9092 --topic twitter --producer-property acks=all

bij versturen van berichten naar een kafka-topic dat nog niet bestaat, krijg je een warning en wordt de topic door kafka aangemaakt.

kafka-consumer cli aanmaken

kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic twitter

vanaf het begin consumeren

kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic twitter --from-beginning

kafka-consumers zijn onderdeel van een group
kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic twitter --group twitterapplication
