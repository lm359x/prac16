package org.example.dog;

import org.example.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DogController {
    @Autowired
    private DogService dogService;

    @GetMapping(value = "/dog/{dogId}/user")
    public @ResponseBody User getDogUser(@PathVariable("dogId")
                                         Long dogId) {
        return dogService.getUserByDog(dogId);
    }
}
