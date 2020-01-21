package pn.user_system.annotations.password;

import pn.user_system.annotations.AnnotationsUtil;
import pn.user_system.constants.TextConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    private static final Pattern PATTERN_LOWER = Pattern.compile("[a-z]");
    private static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");
    private static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]");
    private static final Pattern PATTERN_SYMBOL = Pattern.compile("[!@#$%^&*()_+<>?]");

    private int minLength;
    private int maxLength;
    private boolean hasDigit;
    private boolean hasUpper;
    private boolean hasLower;
    private boolean hasSpecialSymbol;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.hasDigit = password.containsDigit();
        this.hasUpper = password.containsUpperCase();
        this.hasLower = password.containsLowerCase();
        this.hasSpecialSymbol = password.containsSpecialSymbol();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (value.length() < this.minLength) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.PASSWORD_TOO_SHORT);
            return false;
        }

        if (value.length() > this.maxLength) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.PASSWORD_TOO_LONG);
            return false;
        }

        String password = value.toString();

        if (this.hasLower && !PATTERN_LOWER.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER);
            return false;
        }

        if (this.hasUpper && !PATTERN_UPPER.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER);
            return false;
        }

        if (this.hasDigit && !PATTERN_DIGIT.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.PASSWORD_SHOULD_CONTAIN_DIGIT);
            return false;
        }

        if (this.hasSpecialSymbol && !PATTERN_SYMBOL.matcher(password).find()) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.PASSWORD_SHOULD_CONTAIN_SPECIAL_SYMBOL);
            return false;
        }

        return true;
    }
}
