package ua.abond.social.web.rest.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/validation-context.xml"})
public class JwtTokenValidationTest {
    @Autowired
    private Validator validator;

    @Test
    public void testNullTokenId() {
        JwtToken jwtToken = new JwtToken(null);
        Set<ConstraintViolation<JwtToken>> result
                = validator.validate(jwtToken);
        assertThat(result.size(), is(1));
    }

    @Test
    public void testValidToken() {
        JwtToken token = new JwtToken("312321312");
        Set<ConstraintViolation<JwtToken>> validate = validator.validate(token);
        assertThat(validate.size(), is(0));
    }
}