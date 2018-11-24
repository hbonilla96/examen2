package com.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.modelos.Finca;
import com.Repo.RepoFinca;
import com.Repo.RepoProductor;
import com.modelos.Productor;

@Controller
public class ProductorController {

	@Autowired
    RepoProductor repo;

    @Autowired
    RepoFinca repoFarmland;

    /**
     * Get all the producers from the database
     * @param model
     * @return
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllProducers(Model model) {
        model.addAttribute("producer", repo.findAll());
        return "index";
    }
	
	/**
     * Get producer information from the form.
     *
     * @param model
     * @return
     */
	@RequestMapping(value = "/producer")
    public String saveProducer(Model model) {
		model.addAttribute("producer", new Productor());
        return "producer";
    }

	
	/**
     * Save producer to database.
     *
     * @param producer
     * @return
     */
    @RequestMapping(value = "producer", method = RequestMethod.POST)
    public String insertProducer(@ModelAttribute("producer") Productor producer) {
        repo.save(producer);       
        return "redirect:/viewproducer/" + producer.getId();
    }
    
	
	/**
     * View a specific producer by its id (the id is grab from the URI).
     *
     * @param id this is the id of the producer and its located in the URI
     * @param model
     * @return
     */
    @RequestMapping("viewproducer/{id}")
    public String showProducer(@PathVariable Long id, Model model) {
        List<Finca> myFarmlands;
        Productor myProducer = repo.findById(id).get();
    	// I'll get the entity with get()
        model.addAttribute("myProducer", myProducer);

        // Get the producer fincas
        myFarmlands = getFarmlandsByProducer(id);
        model.addAttribute("myFarmlands", myFarmlands);

        // Get the finca coffee types
        for (Finca farmland : myFarmlands) {
            model.addAttribute("coffeByFarmland", farmland.getCoffeTypes());
        }

        return "viewproducer";
    }

    /**
     *  This edits a producer
     *
     * @param id this is the id of the producer and its located in the URI
     * @param model
     * @return
     */
    @RequestMapping("producer/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("producer", repo.findById(id).get());
        return "producer";
    }

    /**
     * Return an ArrayList of fincas by its owner(Producer)
     * @param idProducer
     * @return
     */
    private List<Finca> getFarmlandsByProducer(Long idProducer){
        List<Finca> myFarmlands = new ArrayList<>();
        List<Finca> allFarmlands;

        allFarmlands = repoFarmland.findAll();

        for (Finca farmland : allFarmlands) {
            if(farmland.getProducer().getId() == idProducer){
                myFarmlands.add(farmland);
            }
        }

        return myFarmlands;
    }


    @RequestMapping(value = "/searchproducer")
    public String getProducerName(Model model) {
        model.addAttribute("searchProducer", new Productor());
        return "searchproducer";
    }

    @RequestMapping(value = "/searchproducer", method = RequestMethod.POST)
    public String showFoundProducer(Model model, @ModelAttribute("searchProducer") Productor producer) {

        List<Productor> allProducers =  repo.findByFirstnameContaining(producer.getFirstname());

        model.addAttribute("allProducers", allProducers);

        return "searchproducer";
    }
}
