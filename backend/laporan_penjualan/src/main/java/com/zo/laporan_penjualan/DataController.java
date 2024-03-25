package com.zo.laporan_penjualan;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class DataController {
    @Autowired
    DataService dataService;

    @GetMapping("/rangkuman")
    public List<Map<String, Object>> getRangkuman() {
        return dataService.getRangkuman();
    }

    @GetMapping("/detail")
    public List<Map<String, Object>> getDetail() {
        return dataService.getDetail();
    }
}

