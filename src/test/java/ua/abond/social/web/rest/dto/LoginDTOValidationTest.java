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
public class LoginDTOValidationTest {
    @Autowired
    private Validator validator;

    @Test
    public void testValid() {
        Set<ConstraintViolation<LoginDTO>> validate =
                validator.validate(valid());

        assertThat(validate.size(), is(0));
    }

    @Test
    public void testNullUsername() {
        LoginDTO dto = valid();
        dto.setUsername(null);
        Set<ConstraintViolation<LoginDTO>> validate = validator.validate(dto);
        assertThat(validate.size(), is(1));
    }

    @Test
    public void testNullPassword() {
        LoginDTO dto = valid();
        dto.setPassword(null);
        Set<ConstraintViolation<LoginDTO>> validate = validator.validate(dto);
        assertThat(validate.size(), is(1));
    }

    private static LoginDTO valid() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("SomeLongUsername");
        loginDTO.setPassword("SomeLongAndCorrectPassword");
        loginDTO.setRememberMe(false);
        return loginDTO;
    }
}