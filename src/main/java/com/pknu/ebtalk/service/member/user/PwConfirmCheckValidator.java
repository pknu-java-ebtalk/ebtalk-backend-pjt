package com.pknu.ebtalk.service.member.user;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import java.io.ObjectInputStream;
import java.lang.reflect.Field;

import static org.apache.ibatis.ognl.OgnlRuntime.getFieldValue;

@Log4j2
public class PwConfirmCheckValidator implements ConstraintValidator<PwConfirmCheck, Object> {
    private String message;
    private String pw;
    private String pw_check;

    @Override
    public void initialize(PwConfirmCheck constraintAnnotation) {
        message = constraintAnnotation.message();
        pw = constraintAnnotation.pw();
        pw_check = constraintAnnotation.pw_check();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        String password = getFieldValue(object, pw);
        String password_check = getFieldValue(object, pw_check);
        if(!password.equals(password_check)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("password").addConstraintViolation();
            return false;
        }
        return true;
    }

    // 리플렉션을 이용하여 필드 가져옴
    private String getFieldValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        Field dateField;
        try {
            dateField = clazz.getDeclaredField(fieldName);
            dateField.setAccessible(true);
            Object target = dateField.get(object);
            if (!(target instanceof String)) {
                throw new ClassCastException("casting exception");
            }
            return (String) target;
        } catch (NoSuchFieldException e) {
            log.error("NoSuchFieldException", e);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException", e);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not Found Field");
    }
}
