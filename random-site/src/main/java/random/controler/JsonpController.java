package random.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import random.model.Demo;
import random.support.JsonPController;

import javax.sql.DataSource;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;

/**
 * Created by DaoSui on 2015/10/17.
 */
@JsonPController
@RequestMapping("jsonp")
public class JsonpController {

    @RequestMapping(value = "/{projectId}", method = { GET, HEAD })
    public Demo projectMetadata(@PathVariable("projectId") String projectId) {
       return new Demo(projectId,projectId,projectId);
    }

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "ds", method = GET)
    public DataSource getDs(){
        return dataSource;
    }
}
