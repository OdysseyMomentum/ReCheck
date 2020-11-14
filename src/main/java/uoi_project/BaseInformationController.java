package uoi_project;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

        @GetMapping("/getNodeByUuid")
    public String getNodes(@RequestParam(value = "uuid") String uuid) {
        UOINode node = uoiRepository.findByUuid(uuid);
        System.out.println(node);
        return node.toString();
    }


    @GetMapping("/getAllNodes")
    public String getAllNodes(@RequestParam(value = "level", defaultValue = "ROOM") LEVEL level){
        ArrayList<UOINode> nodes = (ArrayList<UOINode>) uoiRepository.findAll();
        ArrayList<UOINode> levelNodes = new ArrayList<>();
        nodes.forEach(uoiNode -> {
            if(uoiNode.getLevel().equals(level)){
                levelNodes.add(uoiNode);
            }
        });
        System.out.println(nodes);
        System.out.println(levelNodes);
        return levelNodes.toString();
    }

    @PostMapping(path = "/newWithInformation", consumes = "application/json", produces = "application/json")
    public UOINode addMember(@RequestBody UOINode uoiNode) {
        JSONObject js = new JSONObject(uoiNode);
        System.out.println(js.toString());
        uoiRepository.save(uoiNode);
        return uoiNode;
    }



    //search
    //making relations

}
