package bookProject.controller;

import bookProject.DAO.BookDao;
import bookProject.DAO.OrderDao;
import bookProject.domain.Book;
import bookProject.model.*;
import bookProject.service.BookService;
import bookProject.service.BookServiceImpl;
import bookProject.service.OrderService;
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
import java.util.*;

@Controller
@Transactional
@EnableWebMvc
public class BookController {

    private OrderService orderService;
    private BookService bookService;
    private CustomerInfoValidator customerInfoValidator;
    Book book;
    private List<CustomerInfo> customerInfoList = new ArrayList<>();
    private OrderDetailInfo orderDetailInfo;
    private OrderInfo orderInfo;
    private BookInfo bookInfo;
    private CartInfo cartInfo;
    private List<CartLineInfo> cartLineInfoList = new ArrayList<>();
    private List<Cart> cartList = new ArrayList<>();

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
        if (target.getClass() == CartInfo.class) {

        }
        else if (target.getClass() == CustomerInfo.class) {
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

    /*@RequestMapping({ "/buyBook" })
    public String listBookHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "code", defaultValue = "") String code) {

        if (code != null && code.length() > 0) {
            book = bookService.findBook(code);
        }
        if (book != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);
            BookInfo bookInfo = new BookInfo(book);
            cartInfo.addBook(bookInfo, 1);
        }
        return "redirect:/shoppingCart";
    }*/

    @RequestMapping({ "/shoppingCartRemoveBook" })
    public String removeBookHandler(HttpServletRequest request, Model model,
                                       @RequestParam(value = "code", defaultValue = "") String code) {
        Book book = null;
        if (code != null && code.length() > 0) {
            book = (Book) bookService.findBook(code);
        }
        if (book != null) {
            CartInfo cartInfo= Utils.getCartInSession(request);
            BookInfo bookInfo = new BookInfo(book);
            cartInfo.removeBook(bookInfo);
        }
        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        //if (cartInfo.isEmpty()) {
         //   return "redirect:/shoppingCart";
       // }
        CustomerInfo customerForm = cartInfo.getCustomerInfo();
        if (customerForm == null) {
            customerForm = new CustomerInfo();
        }
        model.addAttribute("customerForm", customerForm);
        return "shoppingCartCustomer";
    }

    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request,
                                           Model model,
                                           @ModelAttribute("customerForm") @Validated CustomerInfo customerForm,
                                           BindingResult result,
                                           final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
          customerForm.setValid(false);
          return "shoppingCartCustomer";
        }
        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.setCustomerInfo(customerForm);
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
    }

    @RequestMapping(value = {"/toTheCart"}, method = RequestMethod.GET)
    public String toTheCart(HttpServletRequest request){
        /*String code = request.getParameter("code");
        int quan = Integer.parseInt(request.getParameter("quantity"));
        book= (Book) this.bookService.findBook(code);
        bookInfo = new BookInfo(book);
        CartLineInfo cartLineInfo = new CartLineInfo();
        cartLineInfo.setBookInfo(bookInfo);
        cartLineInfo.setQuantity(quan);
        cartLineInfoList.add(cartLineInfo);
        cartInfo = new CartInfo();
        cartInfo.setCustomerInfo(customerInfo);
        cartInfo.setOrderNum(Integer.parseInt(orderInfo.getId()));*/
        request.setAttribute("booksAll", new BookServiceImpl().allBooks());
        return "shoppingCartCustomer";
    }

    @RequestMapping(value = "/cartOrder")
    public String getCartOrder (HttpServletRequest req,@RequestParam String code,@RequestParam String quantity){
        int quantityInt = Integer.parseInt(quantity);
        String sessionId =  req.getSession().getId();
        String nameCustomer = req.getParameter("name");
        String addressCustomer = req.getParameter("address");
        String emailCustomer = req.getParameter("email");
        String phoneCustomer = req.getParameter("phone");
        Book book = new BookServiceImpl().findBook(code);
        Cart cart = new Cart(book.getId(),book.getNameBook(),book.getPriceBook(),quantityInt,sessionId,
                nameCustomer,addressCustomer,emailCustomer,phoneCustomer);
        cartList.add(cart);
        return "shoppingCart";
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
        String reqId = request.getSession().getId().toString();
        String code= request.getParameter("code");
        String id = code + reqId;
        book = (Book) this.bookService.findBook(code);

        int quanity = 1;
        Date date = new Date();
        Double amount = quanity*book.getPriceBook();
        orderDetailInfo = new OrderDetailInfo(id,code,book.getNameBook(),1,book.getPriceBook(),amount);
        customerInfo.setName(request.getParameter("name"));
        customerInfo.setEmail(request.getParameter("email"));
        customerInfo.setAddress(request.getParameter("address"));
        customerInfo.setPhone(request.getParameter("phone"));
        orderInfo = new OrderInfo(orderDetailInfo.getId(),date,amount,customerInfo.getName(),
                customerInfo.getAddress(), customerInfo.getEmail(), customerInfo.getPhone());
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

}


