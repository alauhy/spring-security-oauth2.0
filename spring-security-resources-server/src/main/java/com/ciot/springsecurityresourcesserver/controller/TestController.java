package com.ciot.springsecurityresourcesserver.controller;

import com.ciot.springsecurityresourcesserver.model.Client;
import com.ciot.springsecurityresourcesserver.model.MongoOauthUser;
import com.ciot.springsecurityresourcesserver.service.OauthClientService;
import com.ciot.springsecurityresourcesserver.service.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    private OauthClientService oauthClientService;
    @Autowired
    private OauthUserService oauthUserService;
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "{\n" +
                "\t\"datas\": {\n" +
                "\t\t\"gpsReeferData\": {\n" +
                "\t\t\t\"containerNo\": \"OOLJ100006\",\n" +
                "\t\t\t\"gpsData\": {\n" +
                "\t\t\t\t\"isRealtime\": \"true\",\n" +
                "\t\t\t\t\"geoLocation\": {\n" +
                "\t\t\t\t\t\"lng\": \"-79.674416\",\n" +
                "\t\t\t\t\t\"lat\": \"43.736324\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"channel\": \"3G\",\n" +
                "\t\t\t\t\"time\": {\n" +
                "\t\t\t\t\t\"dateStr\": \"20200317125004.000\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"speed\": \"0.0\",\n" +
                "\t\t\t\t\"direction\": \"177.0\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"reeferInfo\": {\n" +
                "\t\t\t\t\"eventCode\": {\n" +
                "\t\t\t\t\t\"nil\": \"true\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"sourceOfPower\": \"External\",\n" +
                "\t\t\t\t\"temperatures\": {\n" +
                "\t\t\t\t\t\"ret\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"sup2\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"ret2\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"spnt\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"env\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"evpr\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"sup\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"usda1\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"usda2\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"usda3\": \"1.4E-45\",\n" +
                "\t\t\t\t\t\"usda4\": \"1.4E-45\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"position\": {\n" +
                "\t\t\t\t\"facilityCode\": {\n" +
                "\t\t\t\t\t\"nil\": \"true\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"address\": \"7605-7699 Bath Rd, Mississauga, ON L4T 1G2, Canada\",\n" +
                "\t\t\t\t\"references\": {\n" +
                "\t\t\t\t\t\"iris2Ref\": {\n" +
                "\t\t\t\t\t\t\"type\": \"City\",\n" +
                "\t\t\t\t\t\t\"uuid\": {\n" +
                "\t\t\t\t\t\t\t\"nil\": \"461802935875795\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"additionalReeferInfo\": {\n" +
                "\t\t\t\t\"keyValuePairs\": [{\n" +
                "\t\t\t\t\t\t\"value\": \"GPSRTT-300G-M\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS_DEVICE_MODEL\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"GPS\",\n" +
                "\t\t\t\t\t\t\"key\": \"SOURCE_OF_TIME\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"0\",\n" +
                "\t\t\t\t\t\t\"key\": \"Hours Meter 2\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"51.1\",\n" +
                "\t\t\t\t\t\t\"key\": \"Net Frequency\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"8.5\",\n" +
                "\t\t\t\t\t\t\"key\": \"AC Current (Max)\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"12000041\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Option 1\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"51\",\n" +
                "\t\t\t\t\t\t\"key\": \"Running Frequency\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"210001\",\n" +
                "\t\t\t\t\t\t\"key\": \"Genset Time UTC\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"400\",\n" +
                "\t\t\t\t\t\t\"key\": \"AC Voltage\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"0.61\",\n" +
                "\t\t\t\t\t\t\"key\": \"Field Current\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"key\": \"GPSDetected Freefall 1\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"170925\",\n" +
                "\t\t\t\t\t\t\"key\": \"Genset Date UTC\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"03\",\n" +
                "\t\t\t\t\t\t\"key\": \"Genset Mode\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"0\",\n" +
                "\t\t\t\t\t\t\"key\": \"Engine Restart Timer\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"488\",\n" +
                "\t\t\t\t\t\t\"key\": \"Total Restart\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"1290\",\n" +
                "\t\t\t\t\t\t\"key\": \"Run Hours 60Hz\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Detected Freefall 2\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"08\",\n" +
                "\t\t\t\t\t\t\"key\": \"Engine State\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"151.5\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Fuel Level\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"070200\",\n" +
                "\t\t\t\t\t\t\"key\": \"Last Power Off Time\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"170919\",\n" +
                "\t\t\t\t\t\t\"key\": \"Last Power On Date\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"11089999FFFFFFFF100000\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Option 3\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"0\",\n" +
                "\t\t\t\t\t\t\"key\": \"Fuel Sensor Present\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"01\",\n" +
                "\t\t\t\t\t\t\"key\": \"Extension Present\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"0\",\n" +
                "\t\t\t\t\t\t\"key\": \"Hours Meter 1\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Detected Shock 1\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"16.46\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Board Temperature\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"key\": \"Alarms\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"406.4\",\n" +
                "\t\t\t\t\t\t\"key\": \"AC Voltage (Max)\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"12464\",\n" +
                "\t\t\t\t\t\t\"key\": \"Run Hours 50Hz\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"0\",\n" +
                "\t\t\t\t\t\t\"key\": \"Engine Off Hour\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Cargo Operation 2\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"00253215\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Option 2\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"075400\",\n" +
                "\t\t\t\t\t\t\"key\": \"Last Power On Time\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"1535\",\n" +
                "\t\t\t\t\t\t\"key\": \"Engine RPM\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Cargo Operation 1\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"3093[3083]\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Interface Voltage\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"13754\",\n" +
                "\t\t\t\t\t\t\"key\": \"Run Hours\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"09\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Detected Mode\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"key\": \"Warnings\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"0\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Interface Current\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"170911\",\n" +
                "\t\t\t\t\t\t\"key\": \"Last Power Off Date\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"-1\",\n" +
                "\t\t\t\t\t\t\"key\": \"Genset Fuel Level\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"5.1\",\n" +
                "\t\t\t\t\t\t\"key\": \"Rotor Current\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"400\",\n" +
                "\t\t\t\t\t\t\"key\": \"AC Voltage (Avg)\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"68.3\",\n" +
                "\t\t\t\t\t\t\"key\": \"Water Temperature\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"1\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Power Source\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"1\",\n" +
                "\t\t\t\t\t\t\"key\": \"ECO Power\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"1\",\n" +
                "\t\t\t\t\t\t\"key\": \"Restarts Count\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Detected Shock 2\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"14.29\",\n" +
                "\t\t\t\t\t\t\"key\": \"Battery Voltage\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"value\": \"-----\",\n" +
                "\t\t\t\t\t\t\"key\": \"GPS Facility Code\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"msgMetaData\": {\n" +
                "\t\t\"receiverCode\": \"DeportSmart\",\n" +
                "\t\t\"senderCode\": \"REEFER_APP\",\n" +
                "\t\t\"ctrlRefNo\": \"2227e98a-3083-4b3f-b13d-dc0ce51ebe22\",\n" +
                "\t\t\"version\": \"1.0\",\n" +
                "\t\t\"sendTime\": {\n" +
                "\t\t\t\"dateStr\": \"20200317125038.250\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"receiveDT\": \"2020-03-17T12:50:38Z\"\n" +
                "}";
    }
    @PostMapping("/client")
    public String createClient(@RequestBody Client client) {
        oauthClientService.createClient(client);
        return "success";
    }
    @PostMapping("/user")
    public String createClient(@RequestBody MongoOauthUser user) {
        oauthUserService.createUser(user);
        return "success";
    }


}
