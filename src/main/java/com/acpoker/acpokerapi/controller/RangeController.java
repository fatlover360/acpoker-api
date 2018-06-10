package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.RangeModel;
import com.acpoker.acpokerapi.entity.Type;
import com.acpoker.acpokerapi.service.RangeService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/range")
public class RangeController {

    @Autowired
    private RangeService rangeService;

    @PostMapping("/model/add")
    @CrossOrigin(value = "*")
    public boolean addModel(@RequestBody List<RangeModel> range) {
        return rangeService.addModel(range);
    }

    @PostMapping("/add")
    @CrossOrigin(value = "*")
    public boolean addRanges(@RequestBody List<RangeModel> range) {
        return rangeService.addRange(range);
    }

    @PostMapping("/type/add")
    @CrossOrigin(value = "*")
    public boolean addType(@RequestBody Type type) {
        return rangeService.addType(type);
    }

    @PostMapping("/type/all")
    @CrossOrigin(value = "*")
    public boolean addTypes(@RequestBody List<Type> types) {
        return rangeService.addTypes(types);
    }

    @GetMapping("/find/{type}/{position}/{blind}/{gametype}")
    @CrossOrigin(value = "*")
    public List<RangeModel> getRangesByType(@PathVariable("type") String type, @PathVariable("position") String position, @PathVariable("blind") String blind, @PathVariable("gametype") String gametype) {
        return rangeService.getRangesByTypeAndPosition(type, position, blind, gametype);
    }

    @DeleteMapping("/delete/{type}")
    @CrossOrigin(value = "*")
    public boolean delete(@PathVariable("type") String type) {
        return rangeService.remove(type);
    }

    @GetMapping("/types/all/{gametype}")
    @CrossOrigin(value = "*")
    public List<Type> getAllTypes(@PathVariable String gametype) {
        return rangeService.getTypes(gametype);
    }

    @GetMapping("/default")
    @CrossOrigin(value = "*")
    public List<RangeModel> getDefault() {
        return rangeService.getDefaultRanges();
    }

    @GetMapping("/parse")
    public void parse() {
        try {
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader("C:\\Projects\\ACPOKER\\rangeModel_v0.1.json"));

            JSONArray jsonObject = (JSONArray) obj;

            for (int i = 0; i < jsonObject.size(); i++) {
                HashMap<String, String> jsonObject1 = (HashMap<String, String>) jsonObject.get(i);
                RangeModel rg = new RangeModel();
                String y = (String) jsonObject1.get("gametype");
             /*   rg.setGameType(gametype);
                String blind = (String) jsonObject1.get("blind");
                String color = (String) jsonObject1.get("color");
                String kind = (String) jsonObject1.get("kind");
                String percentage = (String) jsonObject1.get("percentage");
                String position = (String) jsonObject1.get("position");
                String type = (String) jsonObject1.get("percentage");
                String value = (String) jsonObject1.get("position");*/
                jsonObject1.put("gameType", y);
                jsonObject1.remove("gametype");

            }

            // List<RangeModel> rangeModels = ( List<RangeModel>)jsonObject;
            FileWriter file = new FileWriter("C:\\Projects\\ACPOKER\\rangeModel_v0.1_OK.json");

            file.write(jsonObject.toJSONString());
            file.flush();


           // rangeService.addRange(jsonObject);

        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}
