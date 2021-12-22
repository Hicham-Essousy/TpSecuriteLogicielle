package com.example.tpSecurity.controllers;


import com.example.tpSecurity.beans.Client;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Controler {
    List<Client> clients = new ArrayList<>();

    public Controler() {
        clients.add(new Client(1L,"hicham","soussi"));
        clients.add(new Client(2L,"danaway","hestler"));
        clients.add(new Client(3L,"samir","lebdairi"));
    }

    @GetMapping("/home")
    public String home(){
        return "hello world";
    }

    @GetMapping("/clients")
    public List<Client> getAll(){
        return clients;
    }

    @GetMapping("/clients/{id}")
    public Client getOne(@PathVariable int id){
        return clients.get(id-1);
    }

    @PostMapping("/add")
    public Client addOne(@RequestBody Client client){
        clients.add(client);
        return client;
    }




}
