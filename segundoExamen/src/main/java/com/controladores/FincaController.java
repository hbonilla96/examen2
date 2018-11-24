package com.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Repo.RepoCafe;
import com.Repo.RepoFinca;
import com.Repo.RepoProductor;
import com.modelos.Cafe;
import com.modelos.Finca;
import com.modelos.Productor;

@Controller
public class FincaController {

    @Autowired
    RepoFinca repo;

    @Autowired
    RepoCafe repoCoffee;

    @Autowired
    RepoProductor repoProd;

    /**
     * Get farmland information from the form.
     *
     * @param model
     * @return
     */
    @RequestMapping("/farmland/{id}")
    public String saveFinca(Model model, @PathVariable int id) {
        Finca myFarmland = new Finca();

        // Set the producer id that comes from the URI
        myFarmland.setIdProducer(id);
        model.addAttribute("farmland", myFarmland);

        // Get the coffee types
        model.addAttribute("coffee", repoCoffee.findAll());

        return "farmland";
    }


    /**
     * Save finca to database.
     *
     * @param listCoffee get the selected checkboxes
     * @param myFarmland get the name from the form
     * @return
     */
    @RequestMapping(value = "farmland", method = RequestMethod.POST)
    public String insertFinca(@ModelAttribute("farmland") Finca myFarmland, @RequestParam("id") List<String> listCoffee) {
        Long nextFincaId = getLastIdFromTFarmland();
        Cafe myCafe;
        Productor myProducer;

        //Get the producer by Id and stored to the producer object
        myProducer = repoProd.findById((long) myFarmland.getIdProducer()).get();
        myFarmland.setProducer(myProducer);

        // Set the last id inserted in tfarmland
        myFarmland.setId(nextFincaId);

        // Get my cafe type
        // Add all the selected coffee types into the list of coffee type in Farmland
        for (String cofID : listCoffee) {
            myCafe = repoCoffee.findById(Long.valueOf(cofID)).get();
            myFarmland.getCoffeTypes().add(myCafe);
        }

        // Insert finca to table tfarmland and insert into tfarmland_x_tcoffee table
        repo.save(myFarmland);

        return "redirect:/viewproducer/"+ myFarmland.getIdProducer();
    }

    /**
     * Get the last id inserted in the databse tfarmland
     * @return last id
     */
    private Long getLastIdFromTFarmland(){
        Long nextFincaId;

        // Get the last record inserted
        Finca f = repo.findTopByOrderByIdDesc();
        if (f == null){
            nextFincaId = (long) 1;
        }else{
            nextFincaId = f.getId() + 1;
        }

        return nextFincaId;
    }
}
