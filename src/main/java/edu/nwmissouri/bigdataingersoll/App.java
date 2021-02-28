package edu.nwmissouri.bigdataingersoll;

import java.util.Scanner;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;

/**
 * Author: Devin Ingersoll
 * Description: Kafka producer that 
 * prints out pokemon name by number inputed in consumer
 */

public class App  {  
    private static Scanner in;
    public static void main(String[] argv)throws Exception {
        if (argv.length != 1) {
            System.err.println("Please specify 1 parameters ");
            System.exit(-1);
        }

        PokeApiClient pokeApi = new PokeApiClient();
        String topicName = argv[0];
        in = new Scanner(System.in);
        System.out.println("Enter message(type exit to quit)");

        //Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);
        String line = in.nextLine();
        while(! line.equals("exit")) {
            // first verify input is a acceptable number
            if ((line.matches("\\d+$")) && Integer.parseInt(line) < 899 && Integer.parseInt(line) > 0) {
                // transform input into pokemon name
                Pokemon pokeJSON = pokeApi.getPokemon(Integer.parseInt(line));
                String pkmon = pokeJSON.getName();
                // send name into Consumer
                ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, pkmon);
                producer.send(rec);
            }
            line = in.nextLine();
        }
        in.close(); 
        producer.close();
    }
}
