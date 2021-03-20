package com.arc.udemo.model;

import com.arc.udemo.domain.User;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserValidatorTest {
    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @Test
    public void shouldNotValidateWhenUserEmpty() {

        LocaleContextHolder.setLocale(Locale.ENGLISH);
        User user = new User();
        user.setEmail("");
        user.setPassword("");

        Validator validator = createValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.size()).isEqualTo(2);
        ConstraintViolation<User> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
        assertThat(violation.getMessage()).isEqualTo("must not be empty");


        /*for(ConstraintViolation<User> violation : constraintViolations) {
            //ConstraintViolation<User> violation = constraintViolations.iterator().next();
            assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
            assertThat(violation.getMessage()).isEqualTo("must not be empty");

            assertThat(violation.getPropertyPath().toString()).isEqualTo("password");
            assertThat(violation.getMessage()).isEqualTo("must not be empty");
            System.out.println(violation.getMessage());
        }*/
    }

}
