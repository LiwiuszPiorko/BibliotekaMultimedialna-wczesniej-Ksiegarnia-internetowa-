package pl.javastart.exampleproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Book> allBooks = bookRepository.findAll();
        model.addAttribute("books", allBooks);
        return "MainSite";
    }

    @GetMapping("/find")
    @ResponseBody
    public String findByGenre(@RequestParam String genre) {
        Book book = bookRepository.findByGenre(genre);
        return genre;
    }


}
