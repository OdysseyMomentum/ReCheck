package uoi_project;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseInformationController {

    @Autowired
    UOIRepository uoiRepository;

    @GetMapping("/new")
    public UOINode generateNewUOI() {
        double length = 3;
        double height = 3;
        double width = 0.5;
        String owner = "Odyssey";
        String tenant = "Momentum";
        // unique building ID https://github.com/pnnl/buildingid
        String ubid = "849VQJH6+97CVG-1279-797-1043-922";

        //needed for beacons/chip
        double longitude = 0.000001;
        double latitude =0.000001953125;

//        UOINode node = new UOINode(LEVEL.ROOM);
        UOINode node = new UOINode(LEVEL.WALL,length, height, width, owner, tenant, ubid, longitude, latitude);
        uoiRepository.save(node);
        return node;
    }

    @PostMapping(path = "/newWithInformation", consumes = "application/json", produces = "application/json")
    public UOINode addMember(@RequestBody UOINode uoiNode) {
        JSONObject js = new JSONObject(uoiNode);
        System.out.println(js.toString());
        uoiRepository.save(uoiNode);
        return uoiNode;
    }

    //create
    //search
    //making relations

}
