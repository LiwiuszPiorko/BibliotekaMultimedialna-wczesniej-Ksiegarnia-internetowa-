package pl.javastart.exampleproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @RequestMapping("/")
    public String home() {
        return "welcome";
    }

  @GetMapping("/login")
  public String loginForm() {
      return "login";
  }

    @RequestMapping("/MainSite")
    public String home(Model model) {
        List<Book> allBooks = bookRepository.findAll();
        model.addAttribute("books", allBooks);
        return "MainSite";
    }
    @GetMapping("/dodaj")
    public String showForm(Model model) {
        model.addAttribute("book", new Book());
        return "addForm";
    }
    @PostMapping("/zapisz")
    public String saveBook(Book book) {
        bookRepository.save(book);
        return "redirect:MainSite";
    }
    @GetMapping("/singleBook")
    public String singleBook(@RequestParam String author, Model model) {
        Book book = bookRepository.findByAuthor(author);
        model.addAttribute("book", book);
        return "singleBook";
    }
    @GetMapping("category")
    public String showCategory(@RequestParam String genre, Model model) {
        List<Book> booksByGenre = bookRepository.findByGenre(genre);
        model.addAttribute("booksG", booksByGenre);
        return "SortedList";
    }
    @GetMapping("/usuń")
    public String delete(Model model) {
        model.addAttribute("bookD", new Book());
        return "delete";
    }
    @PostMapping("/delete")
    public String deleteBook(@RequestParam String title) {
        bookRepository.deleteByTitle(title);
        return "redirect:MainSite";
    }
    @GetMapping("/edytuj")
    public String edit(Model model) {
        model.addAttribute("bookE", new Book());
        return "edit";
    }
    @PostMapping("/edit")
    public String editBook(Book book) {
        bookRepository.editBook(book);
        return "redirect:MainSite";
    }
}
