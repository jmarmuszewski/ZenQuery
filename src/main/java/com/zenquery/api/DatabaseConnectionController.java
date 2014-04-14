package com.zenquery.api;

import com.zenquery.model.DatabaseConnection;
import com.zenquery.model.dao.DatabaseConnectionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/databaseConnections/{id}")
public class DatabaseConnectionController {
    @Autowired
    private DatabaseConnectionDAO databaseConnectionDAO;

    @Cacheable("api.databaseConnections")
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
    DatabaseConnection find(@PathVariable Integer id) {
		return databaseConnectionDAO.find(id);
	}

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    DatabaseConnection create(
            @RequestBody DatabaseConnection databaseConnection
    ) {
        Number id = databaseConnectionDAO.insert(databaseConnection);

        return databaseConnectionDAO.find(new Long(id.longValue()).intValue());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody String update(
            @PathVariable Integer id,
            @RequestBody DatabaseConnection databaseConnection
    ) {
        databaseConnectionDAO.update(id, databaseConnection);

        return "OK";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) {
        databaseConnectionDAO.delete(id);

        return "OK";
    }
}