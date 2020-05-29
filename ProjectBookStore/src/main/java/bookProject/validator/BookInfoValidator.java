package bookProject.validator;

import bookProject.DAO.BookDao;
import bookProject.domain.Book;
import bookProject.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;


@Component
public class BookInfoValidator implements Validator{

    @Autowired
    private BookDao bookDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == BookInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookInfo bookInfo = (BookInfo) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "NotEmpty.bookForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameBook", "NotEmpty.bookForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "NotEmpty.bookForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.bookForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priceBook", "NotEmpty.bookForm.price");
        String code = bookInfo.getCode();
        if (code != null && code.length() > 0) {
            if (code.matches("\\s+")) {
                errors.rejectValue("code", "Pattern.bookForm.code");
            } else if(bookInfo.isNewBook()) {
                Book book = bookDao.findBook(code);
                if (book != null) {
                    errors.rejectValue("code", "Duplicate.bookForm.code");
                }
            }
        }
    }
}

