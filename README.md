[![BCH compliance](https://bettercodehub.com/edge/badge/OdysseyMomentum/ReCheck?branch=main)](https://bettercodehub.com/)
# ReCheck
Real Estate - Give Buildings a Personality

## Road Map : 

Adding parameters to relationships

adding APIs to create the 

## Tech Stack
- IDE IntelliJ
- Java - 1.8
- neo4j server - 3.5.22
- neo4j driver - 4.1.1

### uoi_nodes structure
 
 ``` {
    private Long id;
    private String uuid;
    private double length;
    private double height;
    private double width;
    private List materials;
    private List physicalID;
    private String timestamp;
    private String owner;
    private String tenant;
    private LEVEL level;
    private String address;
    
     // unique building ID https://github.com/pnnl/buildingid
    private UBID ubid;

    //needed for beacons/chip
    private double longitude;
    private double latitude;
    private List resources;

    private UOINode parent;

    private List<ConsistsOf> children = new ArrayList<>();
    
    private UOINode historyOf;

 }
```

### Relationships Structure

- CONSISTS_OF
```
    {
        Timestamp: 
    }
```

- PART_OF
```
    {
        Timestamp: 
    }
```

- HISTORY_OF
```
    {
        Timestamp: 
        validated: t/f 
    }
```
