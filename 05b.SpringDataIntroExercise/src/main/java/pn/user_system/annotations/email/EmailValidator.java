package pn.user_system.annotations.email;

import pn.user_system.annotations.AnnotationsUtil;
import pn.user_system.constants.TextConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    private int minUsernameLength;
    private int maxUsernameLength;
    private int maxHostNameLength;
    private Pattern pattern;

    @Override
    public void initialize(final Email email) {
        this.minUsernameLength = email.minUsernameLength();
        this.maxUsernameLength = email.maxUsernameLength();
        this.maxHostNameLength = email.maxHostNameLength();
        this.pattern = Pattern.compile((email.regex()));
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        String email = value.toString();
        int usernameLength = email.indexOf("@");
        int hostNameLength = email.length() - email.lastIndexOf("@") - 1;

        if (usernameLength < this.minUsernameLength) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.EMAIL_NAME_LENGTH_TOO_SHORT);
            return false;
        }

        if (usernameLength > this.maxUsernameLength) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.EMAIL_NAME_LENGTH_TOO_LONG);
            return false;
        }

        if (hostNameLength > this.maxHostNameLength) {
            AnnotationsUtil.setErrorMessage(context, TextConstant.EMAIL_HOST_LENGTH_TOO_LONG);
            return false;
        }

        return this.pattern.matcher(email).matches();
    }
}
