package com.example.SpringMovie;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller


public class Controller {
    static final String url ="https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";
        private static RestTemplate restTemplate = new RestTemplate();


    public List<Movie> getMovies(String route){
        ResultsPage resultsPage = restTemplate.getForObject(route, ResultsPage.class);
        return resultsPage.getResults();
    }

        @RequestMapping(path="/", method = RequestMethod.GET)
        public String home(){
            return "home";
        }

        @RequestMapping(path = "/now-playing", method = RequestMethod.GET)
        public String nowPlaying(Model model) {
            List<Movie> movies = getMovies(url);
            model.addAttribute("movies", movies);
            return "now-playing";
        }

        @RequestMapping(path = "/medium-popular-long-name", method = RequestMethod.GET)
        public String mediumPopularLongNames(Model model){
            List<Movie> movies = getMovies(url);
            model.addAttribute("movies", movies
                                .stream()
                                .filter(e -> e.getTitle().length()>=10)
                                .filter(e -> e.getPopularity()>=70 && e.getPopularity()<=200)
                                .collect(Collectors.toList()));
            return "medium-popular-long-name";
        }


}
