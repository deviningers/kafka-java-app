# kafka-java-app



```powershell
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

```powershell
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

```powershell
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic pokeAPI
```

```powershell
mvn clean compile assembly:single
```

```powershell
java -cp target/kafka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdataingersoll.Consumer pokeAPI group1
```

```powershell
java -cp target/kafka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdataingersoll.App pokeAPI
```