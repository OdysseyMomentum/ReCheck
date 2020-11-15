package uoi_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class BaseInformationController {

    @Autowired
    UOIRepository uoiRepository;

    @GetMapping("/new")
    public UOINode generateNewUOI(@RequestParam(value = "level", defaultValue = "ROOM") LEVEL level) {
//        double length = 3;
//        double height = 3;
//        double width = 0.5;
//        String owner = "Odyssey";
//        String tenant = "Momentum";
//        // unique building ID https://github.com/pnnl/buildingid
//        String ubid = "849VQJH6+97CVG-1279-797-1043-922";
//
//        //needed for beacons/chip
//        double longitude = 0.000001;
//        double latitude =0.000001953125;

        UOINode node = new UOINode(level);
//        UOINode node = new UOINode(LEVEL.WALL,length, height, width, owner, tenant, ubid, longitude, latitude);
        uoiRepository.save(node);
        return node;
    }

    @GetMapping("/getNodeByUOI")
    public String getNodes(@RequestParam(value = "uuid") String uuid) {
        UOINode node = uoiRepository.findByUuid(uuid);
        System.out.println(node);
        return node.toString();
    }


    @GetMapping("/getAllNodes")
    public String getAllNodes(@RequestParam(value = "level", defaultValue = "ROOM") LEVEL level) {
        ArrayList<UOINode> nodes = (ArrayList<UOINode>) uoiRepository.findAll();
        ArrayList<UOINode> listNodes = new ArrayList<>();
        nodes.forEach(uoiNode -> {
            listNodes.add(uoiNode);
        });
        return nodes.toString();
    }

    @GetMapping("/setNodeBePartOfAnotherNode")
    public String nodePartOfAnother(@RequestParam(value = "uuid") String uuid, @RequestParam(value = "uuidPartOf") String uuidPartOf) {
        int nodePlace = 999999999;
        int nodePlacePartOf = 999999999;
        ArrayList<UOINode> nodes = (ArrayList<UOINode>) uoiRepository.findAll();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getUuid().equals(uuid)) {
                nodePlace = i;
            }
            if (nodes.get(i).getUuid().equals(uuidPartOf)) {
                nodePlacePartOf = i;
            }
        }
        if (nodePlace < 99999999) {
            if (nodePlacePartOf < 99999999) {
                nodes.get(nodePlacePartOf).partOf(nodes.get(nodePlace));
                uoiRepository.saveAll(nodes);
            }
        }
        ArrayList<UOINode> usedNodes = new ArrayList();
        usedNodes.add(nodes.get(nodePlacePartOf));
        usedNodes.add(nodes.get(nodePlace));
        return usedNodes.toString();
    }

    @GetMapping("/setNodeBeConsistedOfAnotherNode")
    public String nodeConsistedOfAnother(@RequestParam(value = "uuid") String uuid, @RequestParam(value = "uuidConsistedOf") String uuidConsistedOf) {
        int nodePlace = 999999999;
        int nodePlaceConsistedOf = 999999999;
        ArrayList<UOINode> nodes = (ArrayList<UOINode>) uoiRepository.findAll();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getUuid().equals(uuidConsistedOf)) {
                nodePlaceConsistedOf = i;
            }
            if (nodes.get(i).getUuid().equals(uuid)) {
                nodePlace = i;
            }
        }
        if (nodePlace < 99999999) {
            if (nodePlaceConsistedOf < 99999999) {
                nodes.get(nodePlace).consistsOf(nodes.get(nodePlaceConsistedOf));
                uoiRepository.saveAll(nodes);
            }
        }
        ArrayList<UOINode> usedNodes = new ArrayList();
        usedNodes.add(nodes.get(nodePlaceConsistedOf));
        usedNodes.add(nodes.get(nodePlace));
        return usedNodes.toString();
    }

    @GetMapping("/setNodeBeHistoryOfAnotherNode")
    public String nodeHistoryOfAnother(@RequestParam(value = "uuid") String uuid, @RequestParam(value = "uuidHistoryOf") String uuidHistoryOf) {
        int nodePlace = 999999999;
        int nodePlaceHistoryOf = 999999999;
        ArrayList<UOINode> nodes = (ArrayList<UOINode>) uoiRepository.findAll();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getUuid().equals(uuid)) {
                nodePlace = i;
            }
            if (nodes.get(i).getUuid().equals(uuidHistoryOf)) {
                nodePlaceHistoryOf = i;
            }
        }
        if (nodePlace < 99999999) {
            if (nodePlaceHistoryOf < 99999999) {
                nodes.get(nodePlaceHistoryOf).historyOf(nodes.get(nodePlace));
                uoiRepository.saveAll(nodes);
            }
        }
        ArrayList<UOINode> usedNodes = new ArrayList();
        usedNodes.add(nodes.get(nodePlaceHistoryOf));
        usedNodes.add(nodes.get(nodePlace));
        return usedNodes.toString();
    }

    @PostMapping(path = "/newWithInformation", consumes = "application/json", produces = "application/json")
    public UOINode addMember(@RequestBody UOINode uoiNode) {
        uoiRepository.save(uoiNode);
        return uoiNode;
    }


    //making relations

}
