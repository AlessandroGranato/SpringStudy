package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

     /*
    @RequestMapping(method= RequestMethod.GET)
    public String spittles(Model model) {

        //When key is not specified in the method addAttribute of Model, the key is inferred from the type of object being set as the value. In this case, because it’s a List<Spittle>, the key will be inferred as spittleList.
        //model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));

        //This is instead the explicit key-value assignment
        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));

        return "spittles";
    }
    */

    //Model is a SpringType. Here we can see the same method with the java.util.Map object instead of Model object
    /*
    @RequestMapping(method=RequestMethod.GET)
    public String spittles(Map model) {
        model.put("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }
     */

    @RequestMapping(method= RequestMethod.GET)
    public List<Spittle> spittles(@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max, @RequestParam(value="count", defaultValue="20") int count) {
        return spittleRepository.findSpittles(max, count);
    }

    @RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
    public String spittle(@PathVariable("spittleId") long spittleId,  Model model) {
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }


}