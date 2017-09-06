package api;

import io.dropwizard.jersey.validation.Validators;
import org.junit.Test;

import javax.validation.Validator;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class CreateTagRequestTest {
    private final Validator validator = Validators.newValidator();

    @Test
    public void testTagValid() {
        CreateTagRequest tag = new CreateTagRequest();
        tag.tag = "TAG TEST";

        assertThat(validator.validate(tag), empty());
    }
}
