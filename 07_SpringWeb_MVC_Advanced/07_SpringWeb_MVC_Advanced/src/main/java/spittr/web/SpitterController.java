package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value="/register", method=GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value="/register", method=POST)
    public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture,
                                      @Valid Spitter spitter,
                                      Errors errors) {
        try {
            if (errors.hasErrors()) {
                return "registerForm";
            }
            System.out.println("processRegistration - Spitter: " + spitter.toString());
            System.out.println("processRegistration - profilePicture.getContentType: " + profilePicture.getContentType());
            System.out.println("processRegistration - profilePicture.getOriginalFilename: " + profilePicture.getOriginalFilename());
            System.out.println("processRegistration - profilePicture.getSize: " + profilePicture.getSize());
            System.out.println("processRegistration - profilePicture.isEmpty: " + profilePicture.isEmpty());
            Path pathToFile = Paths.get(profilePicture.getName());
            System.out.println("processRegistration - pathToFile.toAbsolutePath: " + pathToFile.toAbsolutePath());

            profilePicture.transferTo(new File("C:\\Users\\Alex-Home\\Desktop\\Project_Images\\" + profilePicture.getOriginalFilename()));
            spitterRepository.save(spitter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value="/{username}", method=GET)
    public String showSpitterProfile(
            @PathVariable String username, Model model) {
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }
}