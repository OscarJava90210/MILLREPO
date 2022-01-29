package com.example.mill.machine;

import com.example.mill.bean.MillService;
import com.example.mill.bean.MillState;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/rest/mill")
public class MillController {

    private final MillService millService;

    public MillController(MillService millService) {
        this.millService = millService;
    }

    @GetMapping
    public MillState state() {

        return millService.getState();
    }
    @PostMapping("/water/{capacity}")
    public String  addWater(@PathVariable Integer capacity) {

        millService.addWater(capacity);
        return  "OK";
    }
    @PostMapping("/millet/{capacity}")
    public String  addMillet(@PathVariable Integer capacity) {

        millService.addMillet(capacity);
        return "OK";
    }

    @PostMapping("/flour/drop")
    public String  dropFlour () {

    millService.dropFlour();
    return "OK";
    }

}
