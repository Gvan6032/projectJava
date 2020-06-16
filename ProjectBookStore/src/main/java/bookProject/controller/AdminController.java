package bookProject.controller;

import bookProject.DAO.BookDao;
import bookProject.DAO.OrderDao;
import bookProject.DAO.OrderDaoImpl;
import bookProject.domain.Book;
import bookProject.domain.OrderDetail;
import bookProject.model.Cart;
import bookProject.model.OrderDetailInfo;
import bookProject.model.OrderInfo;
import bookProject.model.Pagination;
import bookProject.service.BookService;
import bookProject.service.BookServiceImpl;
import bookProject.service.OrderService;
import bookProject.validator.BookInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@Transactional
@EnableWebMvc
public class AdminController {

    private OrderService orderService;
    private BookService bookService;
    private BookInfoValidator bookInfoValidator;
    private ResourceBundleMessageSource messageSource;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
        if (target.getClass() == Cart.class) {
            dataBinder.setValidator(bookInfoValidator);
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
        model.addAttribute("userDetails", userDetails);
        return "userInfo";
    }

    @RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model,
                            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
        Pagination<OrderInfo> paginationResult
                = orderService.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }

    /*@RequestMapping(value = { "/book" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        Cart cart = null;
        if (code != null && code.length() > 0) {
            bookInfo = bookService.findBookInfo(code);
        }
        if (bookInfo == null) {
            bookInfo = new BookInfo();
            bookInfo.setNewBook(true);
        }
        model.addAttribute("bookForm", bookInfo);
        return "book";
    }

    @RequestMapping(value = { "/book" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String productSave(Model model, //
                              @ModelAttribute("bookForm") @Validated BookInfo bookInfo, //
                              BindingResult result, //
                              final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "book";
        }
        try {
            bookService.save(bookInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "book";
        }
        return "redirect:/bookList";
    }*/

    @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderService.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/orderList";
        }
        List<OrderDetail> details = this.orderService.listOrderDetailInfos(orderId);
        //orderInfo.setDetails(details);
        model.addAttribute("orderInfo", orderInfo);
        return "order";
    }

    @RequestMapping("/editBook")
    public ModelAndView getEditBook() {
        List<Book> booksAll = new BookServiceImpl().allBooks();
        ModelAndView mav = new ModelAndView("allBooksForAdmin");
        mav.addObject("booksAll",booksAll);
        return mav;
    }

    @GetMapping("/edit")
    @ResponseBody
    public ModelAndView editBookForm(@RequestParam String id) {
        ModelAndView mav = new ModelAndView("editBook");
        Book book = new BookServiceImpl().findBook(id);
        new BookServiceImpl().save(book);
        mav.addObject("book", book);
        return mav;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteCustomerForm(@RequestParam String id) {
        ModelAndView mav = new ModelAndView("allBooksForAdmin");
        Book book = new BookServiceImpl().findBook(id);
        new BookServiceImpl().delete(book);
        return mav;
    }








































































    @RequestMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        Book book = new Book();
        model.put("book", book);
        return "newBook";
    }
}
