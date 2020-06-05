package bookProject.controller;

import bookProject.DAO.BookDao;
import bookProject.DAO.OrderDao;
import bookProject.domain.Book;
import bookProject.domain.Order;
import bookProject.model.*;
import bookProject.service.BookService;
import bookProject.service.BookServiceImpl;
import bookProject.service.OrderService;
import bookProject.service.OrderServiceImpl;
import bookProject.util.Utils;
import bookProject.validator.CustomerInfoValidator;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
@Transactional
@EnableWebMvc
public class BookController {

    private OrderService orderService;
    private BookService bookService;
    private CustomerInfoValidator customerInfoValidator;
    Book book;
    Cart cart;
    private List<CustomerInfo> customerInfoList = new ArrayList<>();
    private List<Cart> cartList = new ArrayList<>();

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
        if (target.getClass() == Cart.class) {

        }
        else if (target.getClass() == Cart.class) {
            dataBinder.setValidator(customerInfoValidator);
        }
    }

    @RequestMapping(value = "/403")
    public String accessDenied() {
        return "/403";
    }

    @RequestMapping(value= "/")
    public String home() {
        return "index";
    }

    @RequestMapping({ "/bookList" })
    public String listBookHandler(Model model, //
                                     @RequestParam(value = "name", defaultValue = "") String likeName,
                                     @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
        likeName = "";
        Pagination<BookInfo> result = bookService.queryBooks(page, //
                maxResult, maxNavigationPage, likeName);
        model.addAttribute("paginationBooks", result);
        return "bookList";
    }

    @RequestMapping(value = "/allBooks")
    public String getAllBooks (HttpServletRequest req){
        req.setAttribute("booksAll", new BookServiceImpl().allBooks());
        return "allBooks";
    }

    /*@RequestMapping({ "/shoppingCartRemoveBook" })
    public String removeBookHandler(HttpServletRequest request, Model model,
                                       @RequestParam(value = "code", defaultValue = "") String code) {
        Book book = null;
        if (code != null && code.length() > 0) {
            book = (Book) bookService.findBook(code);
        }
        if (book != null) {
            Cart cart= Utils.getCartInSession(request);
            BookInfo bookInfo = new BookInfo(book);
            cartInfo.removeBook(bookInfo);
        }
        return "redirect:/shoppingCart";
    }*/

    /*@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request,
                                        Model model,
                                        @ModelAttribute("cartForm") Cart cartForm) {
        Cart cart = Utils.getCartInSession(request);
        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        Cart myCart = Utils.getCartInSession(request);
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

    /*@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
        Cart cart = Utils.getCartInSession(request);
        if (cart.isEmpty()) {
            return "redirect:/shoppingCart";
        }
        model.addAttribute("customerForm", cart);
        return "shoppingCartCustomer";
    }

    /*@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request,
                                           Model model,
                                           @ModelAttribute("customerForm") @Validated Cart customerForm,
                                           BindingResult result,
                                           final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
          return "shoppingCartCustomer";
        }
        Cart cart = Utils.getCartInSession(request);
        cart.setBookCode();
        return "redirect:/shoppingCartConfirmation";
    }

    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        //if (cartInfo.isEmpty()) {
           // return "redirect:/shoppingCart";
       // } else if (!cartInfo.isValidCustomer()) {
          //  return "redirect:/shoppingCartCustomer";
       // }
        return "shoppingCartConfirmation";
    }

    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        if (cartInfo.isEmpty()) {
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderService.saveOrder(cartInfo);
        } catch (Exception e) {
            return "shoppingCartConfirmation";
        }
        Utils.removeCartInSession(request);
        Utils.storeLastOrderedCartInSession(request, cartInfo);
        return "redirect:/shoppingCartFinalize";
    }

    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
        return "shoppingCartFinalize";
    }

    @RequestMapping(value = { "/bookImage" }, method = RequestMethod.GET)
    public void bookImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("code") String code) throws IOException {
        Book book = null;
        if (code != null) {
            book = (Book) this.bookService.findBook(code);
        }
        if (book != null && book.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(book.getImage());
        }
        response.getOutputStream().close();
    }*/

    @RequestMapping(value = {"/toTheCart"}, method = RequestMethod.GET)
    public String toTheCart(HttpServletRequest request,Map<String,Object> model){
        List<Book> bookList = new ArrayList<>();
        bookList = new BookServiceImpl().allBooks();
        request.setAttribute("booksAll",bookList);
        Cart cart = new Cart();
        model.put("customerForm",cart);
        return "shoppingCartCustomer";
    }

    @RequestMapping(value = {"/cartForBuy"},method = RequestMethod.POST)
    public String getCartForBuy (HttpServletRequest req,
                                 Model model,
                                 @ModelAttribute("customerForm") @Validated Cart cart,
                                 BindingResult result,
                                 final RedirectAttributes redirectAttributes){
         if(result.hasErrors()) {
             return "shoppingCart";
         }
         if (req.getParameter("codeBook").equals("Select")||req.getParameter("quantity").equals("0")||
                 (req.getParameter("codeBook").equals("Select")&& req.getParameter("quantity").equals("0"))){
             return "shoppingCart";
         }
         String sessionId =  req.getSession().getId();
         cart.setOrderNum(sessionId);
         cart.setBookCode(req.getParameter("codeBook"));
         Book book= new BookServiceImpl().findBook(cart.getBookCode());
         cart.setBookName(book.getNameBook());
         cart.setBookPrice(book.getPriceBook());
         cart.setQuantity(req.getParameter("quantity"));
         Double amount = cart.getBookPrice()*Integer.parseInt(cart.getQuantity());
         cart.setAmount(amount);
         cart.setStatus("Not paid");
         cartList.add(cart);
         return "addCart";
    }

    @RequestMapping(value = {"shoppingCart"},method = RequestMethod.GET)
    public String getShoppingCart(HttpServletRequest req){
        req.setAttribute("cartForm",cartList);
        return "cart";
    }

    @RequestMapping(value = {"/buyFromCart"},method = RequestMethod.GET)
    public String getMyCart (HttpServletRequest req){
        req.setAttribute("myCart",cartList);
        return "cart";
    }

    @RequestMapping(value = {"/continueBuy"},method = RequestMethod.GET)
    public String continueBuy(HttpServletRequest req)
    {
        OrderService orderService = new OrderServiceImpl();
        orderService.saveOrder(cart);
        return "order";
    }

    @RequestMapping(value = { "/bookOrder" }, method = RequestMethod.POST)
    public String getBookCode(HttpServletRequest request,
                              Model model,
                              @ModelAttribute("customerOrder") @Validated CustomerInfo customerInfo,
                              BindingResult result)
    {
        if (result.hasErrors()) {
            return "shoppingCartCustomer";
        }
        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = "/searchBook")
    public String searchBook (){
        return "searchBook";
    }

    @RequestMapping(value = "/searchResult")
    public String getResultSearch (HttpServletRequest req,@RequestParam String keyword){
        req.setAttribute("result", new BookServiceImpl().search(keyword));
        return "searchResult";
    }

    @RequestMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        Book book = new Book();
        model.put("book", book);
        return "newBook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(HttpServletRequest req,@ModelAttribute("book") Book book) throws ParseException {
        new BookServiceImpl().save(book);
        return "allBooks";
    }
}


