# kafka-java-app
## Description

This project was made to practice creating Apache Kafka applications in Java in my Big Data course.

This App.java kafka producer utilizes a Pokemon API created by sargunv to generate pokemon names from their pokedex number that is inputed.
The kafka consumer, which was copied from the denisecase's kafka-api repo then prints the requested pokemon's name. 

## Commands Used

First we need to start the Kafka and Zookeeper servers using the default settings (needs to be done in two seperate Admin Powershell windows):
```powershell
.\bin\windows\kafka-server-start.bat .\config\server.properties
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```
Next, we create our Kafka topic which can be any string, I used ``pokeAPI``: 
```powershell
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic pokeAPI
```

After this we need to get an executable fat .jar file that contains all of our dependencies, to get this using maven we run (in the root directory of repo):
```powershell
mvn clean compile assembly:single
```

Finally, we start the consumer (takes in two inputs, topic and groupId), and producer (takes in topic): 
```powershell
java -cp target/kafka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdataingersoll.Consumer pokeAPI group1
java -cp target/kafka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdataingersoll.App pokeAPI
```

## Used repos:
(pokekotlin)[https://github.com/PokeAPI/pokekotlin]
(Dr. Case's Kafka API)[https://github.com/denisecase/kafka-api]