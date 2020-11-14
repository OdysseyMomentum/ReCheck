package uoi_project;

import org.neo4j.ogm.annotation.Relationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
public class AccessingDataNeo4jApplication {

    private final static Logger log = LoggerFactory.getLogger(AccessingDataNeo4jApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AccessingDataNeo4jApplication.class, args);
        System.exit(0);
    }

    public void demoNodes(UOIRepository uoiRepository){
        List<UOINode> nodes = new ArrayList<UOINode>();

        //walls
        UOINode wall1 = new UOINode(LEVEL.WALL);
        UOINode wall2 = new UOINode(LEVEL.WALL);
        UOINode wall3 = new UOINode(LEVEL.WALL);
        UOINode wall4 = new UOINode(LEVEL.WALL);

        nodes.add(wall1);
        nodes.add(wall2);
        nodes.add(wall3);
        nodes.add(wall4);

        UOINode room1 = new UOINode(LEVEL.ROOM);

        nodes.add(room1);

        UOINode wall5 = new UOINode(LEVEL.WALL);
        UOINode wall6 = new UOINode(LEVEL.WALL);
        UOINode wall7 = new UOINode(LEVEL.WALL);
        UOINode wall8 = new UOINode(LEVEL.WALL);

        nodes.add(wall5);
        nodes.add(wall6);
        nodes.add(wall7);
        nodes.add(wall8);

        UOINode room2 = new UOINode(LEVEL.ROOM);

        nodes.add(room2);

        UOINode unit1 = new UOINode(LEVEL.UNIT);

        nodes.add(unit1);

        uoiRepository.saveAll(nodes);

        //adding the walls to a room1
        wall1.partOf(room1);
        wall2.partOf(room1);
        wall3.partOf(room1);
        wall4.partOf(room1);

        uoiRepository.saveAll(nodes);

        //room1 is consisting of the walls
        room1.consistsOf(wall1);
        room1.consistsOf(wall2);
        room1.consistsOf(wall3);
        room1.consistsOf(wall4);

        uoiRepository.saveAll(nodes);
        //adding the walls to a room2
        wall5.partOf(room2);
        wall6.partOf(room2);
        wall7.partOf(room2);
        wall8.partOf(room2);

        uoiRepository.saveAll(nodes);

        //room2 is consisting of the walls
        room2.consistsOf(wall5);
        room2.consistsOf(wall6);
        room2.consistsOf(wall7);
        room2.consistsOf(wall8);

        uoiRepository.saveAll(nodes);

        //adding the rooms to unit 1
        room1.partOf(unit1);
        room2.partOf(unit1);

        uoiRepository.saveAll(nodes);

        //the unit is consisted of
        unit1.consistsOf(room1);
        unit1.consistsOf(room2);

        uoiRepository.saveAll(nodes);
    }

    public void demoNodesCombineTwoRooms(UOIRepository uoiRepository){
        List<UOINode> nodes = new ArrayList<UOINode>();

        //walls
        UOINode wall1 = new UOINode(LEVEL.WALL);
        UOINode wall2 = new UOINode(LEVEL.WALL);
        UOINode wall3 = new UOINode(LEVEL.WALL);
        UOINode wall4 = new UOINode(LEVEL.WALL);

        nodes.add(wall1);
        nodes.add(wall2);
        nodes.add(wall3);
        nodes.add(wall4);

        UOINode room1 = new UOINode(LEVEL.ROOM);

        nodes.add(room1);

        UOINode wall5 = new UOINode(LEVEL.WALL);
        UOINode wall6 = new UOINode(LEVEL.WALL);
        UOINode wall7 = new UOINode(LEVEL.WALL);
        UOINode wall8 = new UOINode(LEVEL.WALL);

        nodes.add(wall5);
        nodes.add(wall6);
        nodes.add(wall7);
        nodes.add(wall8);

        UOINode room2 = new UOINode(LEVEL.ROOM);

        nodes.add(room2);

        // adding the new room that combines the two initial rooms
        // combining two rooms
        UOINode roomCombined = new UOINode(LEVEL.ROOM);
        nodes.add(roomCombined);

        UOINode unit1 = new UOINode(LEVEL.UNIT);

        nodes.add(unit1);

        uoiRepository.saveAll(nodes);


        //adding the walls to a room1
        wall1.partOf(roomCombined);
        wall2.partOf(roomCombined);
        wall3.partOf(roomCombined);
        wall4.partOf(roomCombined);

        uoiRepository.saveAll(nodes);

        //room1 is consisting of the walls
        roomCombined.consistsOf(wall1);
        roomCombined.consistsOf(wall2);
        roomCombined.consistsOf(wall3);
        roomCombined.consistsOf(wall4);

        uoiRepository.saveAll(nodes);
        //adding the walls to a room2
        wall5.partOf(roomCombined);
        wall6.partOf(roomCombined);

        uoiRepository.saveAll(nodes);

        //room2 is consisting of the walls
        roomCombined.consistsOf(wall5);
        roomCombined.consistsOf(wall6);

        uoiRepository.saveAll(nodes);

        //adding the room to unit 1
        roomCombined.partOf(unit1);

        uoiRepository.saveAll(nodes);

        //the unit is consisted of
        unit1.consistsOf(roomCombined);
        uoiRepository.saveAll(nodes);

        //History of the rooms
        room1.historyOf(roomCombined);
        room2.historyOf(roomCombined);
        uoiRepository.saveAll(nodes);
    }

    public void demoNodesAddANewRoom(UOIRepository uoiRepository){
        List<UOINode> nodes = new ArrayList<UOINode>();

        //walls
        UOINode wall1 = new UOINode(LEVEL.WALL);
        UOINode wall2 = new UOINode(LEVEL.WALL);
        UOINode wall3 = new UOINode(LEVEL.WALL);
        UOINode wall4 = new UOINode(LEVEL.WALL);

        nodes.add(wall1);
        nodes.add(wall2);
        nodes.add(wall3);
        nodes.add(wall4);

        UOINode room1 = new UOINode(LEVEL.ROOM);

        nodes.add(room1);

        UOINode wall5 = new UOINode(LEVEL.WALL);
        UOINode wall6 = new UOINode(LEVEL.WALL);
        UOINode wall7 = new UOINode(LEVEL.WALL);
        UOINode wall8 = new UOINode(LEVEL.WALL);

        nodes.add(wall5);
        nodes.add(wall6);
        nodes.add(wall7);
        nodes.add(wall8);

        UOINode room2 = new UOINode(LEVEL.ROOM);

        nodes.add(room2);

        UOINode wall9 = new UOINode(LEVEL.WALL);
        UOINode wall10 = new UOINode(LEVEL.WALL);

        nodes.add(wall9);
        nodes.add(wall10);

        UOINode room3 = new UOINode(LEVEL.ROOM);

        nodes.add(room3);

        UOINode unit1 = new UOINode(LEVEL.UNIT);

        nodes.add(unit1);

        uoiRepository.saveAll(nodes);

        //adding the walls to a room1
        wall1.partOf(room1);
        wall2.partOf(room1);
        wall3.partOf(room1);
        wall4.partOf(room1);

        uoiRepository.saveAll(nodes);

        //room1 is consisting of the walls
        room1.consistsOf(wall1);
        room1.consistsOf(wall2);
        room1.consistsOf(wall3);
        room1.consistsOf(wall4);

        uoiRepository.saveAll(nodes);
        //adding the walls to a room2
        wall5.partOf(room2);
        wall6.partOf(room2);
        wall7.partOf(room2);
        wall8.partOf(room2);

        uoiRepository.saveAll(nodes);

        //room2 is consisting of the walls
        room2.consistsOf(wall5);
        room2.consistsOf(wall6);
        room2.consistsOf(wall7);
        room2.consistsOf(wall8);

        uoiRepository.saveAll(nodes);

        //adding the walls to a room3
        wall4.partOf(room3);
        wall8.partOf(room3);
        wall9.partOf(room3);
        wall10.partOf(room3);

        uoiRepository.saveAll(nodes);

        //room2 is consisting of the walls
        room3.consistsOf(wall4);
        room3.consistsOf(wall8);
        room3.consistsOf(wall9);
        room3.consistsOf(wall10);

        uoiRepository.saveAll(nodes);

        //adding the rooms to unit 1
        room1.partOf(unit1);
        room2.partOf(unit1);
        room3.partOf(unit1);

        uoiRepository.saveAll(nodes);

        //the unit is consisted of
        unit1.consistsOf(room1);
        unit1.consistsOf(room2);
        unit1.consistsOf(room3);

        uoiRepository.saveAll(nodes);
    }

    public void testing(UOIRepository uoiRepository){
        UOINode greg = new UOINode(LEVEL.GREG);
        System.out.println("greg "+  greg.toString());
        UOINode roy = new UOINode(LEVEL.ROY);
        System.out.println("roy "+  roy.toString());
        UOINode pepi = new UOINode(LEVEL.EMO);
        System.out.println("Emo "+  pepi.toString());
        UOINode daka = new UOINode(LEVEL.DAKA);
        System.out.println("daka "+  daka.toString());

        List<UOINode> nodes = new ArrayList<UOINode>();
        nodes.add(greg);
        nodes.add(roy);
        nodes.add(pepi);
        nodes.add(daka);

        uoiRepository.saveAll(nodes);

        //old room
        greg.historyOf(pepi);
        uoiRepository.save(greg);

        //old room
        roy.historyOf(pepi);
        uoiRepository.save(roy);

        pepi.partOf(daka);
//            partOf.setUoiNodePartOf(pepi);
//            partOf.setUoiNode(daka);
//            partOf.setTimestamp(String.valueOf(new Date().getTime()));
//            uoiRepository.saveAll(nodes);
        uoiRepository.save(pepi);


        // relationship entity
//            ConsistsOf child = new ConsistsOf();
//
//            child.setUoiNodeChild(pepi);
//            child.setUoiNodeParent(daka);
//
//            child.setTimestamp(String.valueOf(new Date().getTime()));
//
//            List<ConsistsOf> children = new ArrayList<>();
//
//            children.add(child);

        daka.consistsOf(pepi);

//            uoiRepository.save(pepi);
        uoiRepository.save(daka);

    }

    @Bean
    CommandLineRunner deleteDB(UOIRepository uoiRepository){
        return args -> {
         uoiRepository.deleteAll();
        };
    }

    @Bean
    CommandLineRunner demo(UOIRepository uoiRepository) {
        return args -> {
//            testing(uoiRepository);
            demoNodes(uoiRepository);
//            demoNodesCombineTwoRooms(uoiRepository);
//            demoNodesAddANewRoom(uoiRepository);
        };
    }

}