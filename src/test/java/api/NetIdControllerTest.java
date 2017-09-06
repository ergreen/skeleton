package api;

import controllers.NetIdController;
import io.dropwizard.jersey.validation.Validators;
import org.junit.Test;

import javax.validation.Validator;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class NetIdControllerTest {

    private final Validator validator = Validators.newValidator();

    @Test
    public void testGetNetId() {
        NetIdController netId = new NetIdController();
        assertThat("Net ID is correct",netId.getNetId() == "erg53");
    }
}
