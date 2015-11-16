package com.ss.zemluk.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ss.zemluk.dal.DataService;
import com.ss.zemluk.web.jsonview.Views;
import com.ss.zemluk.web.model.AjaxResponseBody;
import com.ss.zemluk.web.model.Data;
import com.ss.zemluk.web.model.RequestData;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Controller to load page and perform data manipulation at page
 */
@Controller
public class DataController {

    @Autowired
    private DataService fileDataService;

    private List<Data> dataList;
    private Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "index";
    }

    /**
     * Initializing view data
     */
    @PostConstruct
    private void iniDataForTesting() {
        dataList = fileDataService.getAll();
    }

    /**
     * Deleting selected value
     *
     * @param requestData Information about the data to delete
     * @return Updated data list
     */
    @JsonView(Views.Public.class)
    @ResponseBody
    @RequestMapping(value = "/data/delete", method = RequestMethod.POST)
    public AjaxResponseBody deleteData(@RequestBody RequestData requestData) {
        log.info("Executing /data/delete");
        AjaxResponseBody result = new AjaxResponseBody();
        if (!StringUtils.isEmpty(requestData.getPosition()) && requestData.getPosition() >= 0) {
            fileDataService.delete(requestData.getPosition());
            result.setCode("200");
            result.setMsg("Value deleted");
            result.setResult(fileDataService.getAll());
        } else {
            result.setCode("406");
            result.setMsg("Value doesn't deleted");
            result.setResult(fileDataService.getAll());
        }
        return result;
    }

    /**
     * Method for moving up data in list
     *
     * @param requestData Information about the data to be moved up
     * @return Updated data list
     */
    @JsonView(Views.Public.class)
    @ResponseBody
    @RequestMapping(value = "/data/moveUp", method = RequestMethod.POST)
    public AjaxResponseBody moveUpData(@RequestBody RequestData requestData) {
        log.info("Executing /data/moveUp");
        AjaxResponseBody result = new AjaxResponseBody();
        if (!StringUtils.isEmpty(requestData.getPosition()) && requestData.getPosition() > 0) {
            Data tmpData = dataList.get(requestData.getPosition() - 1);
            dataList.set(requestData.getPosition() - 1, dataList.get(requestData.getPosition()));
            dataList.set(requestData.getPosition(), tmpData);
            fileDataService.updateAll(dataList);
            result.setCode("200");
            result.setMsg("Value moved up");
            result.setResult(dataList);
        } else {
            result.setCode("406");
            result.setMsg("Value doesn't moved up");
            result.setResult(dataList);
        }
        return result;
    }

    /**
     * Method for moving down data in list
     *
     * @param requestData Information about the data to be moved down
     * @return Updated data list
     */
    @JsonView(Views.Public.class)
    @ResponseBody
    @RequestMapping(value = "/data/moveDown", method = RequestMethod.POST)
    public AjaxResponseBody moveDownData(@RequestBody RequestData requestData) {
        log.info("Executing /data/moveDown");
        AjaxResponseBody result = new AjaxResponseBody();
        if (!StringUtils.isEmpty(requestData.getPosition()) && requestData.getPosition() >= 0 && requestData.getPosition() + 1 < dataList.size()) {
            Data tmpData = dataList.get(requestData.getPosition() + 1);
            dataList.set(requestData.getPosition() + 1, dataList.get(requestData.getPosition()));
            dataList.set(requestData.getPosition(), tmpData);
            fileDataService.updateAll(dataList);
            result.setCode("200");
            result.setMsg("Value moved down");
            result.setResult(dataList);
        } else {
            result.setCode("406");
            result.setMsg("Value doesn't moved down");
            result.setResult(dataList);
        }
        return result;
    }

    /**
     * Method for updating data in list
     *
     * @param requestData Information about the data to be updated
     * @return Updated data list
     */
    @JsonView(Views.Public.class)
    @ResponseBody
    @RequestMapping(value = "/data/update", method = RequestMethod.POST)
    public AjaxResponseBody updateData(@RequestBody RequestData requestData) {
        log.info("Executing /data/update");
        AjaxResponseBody result = new AjaxResponseBody();
        if (!StringUtils.isEmpty(requestData.getPosition()) && requestData.getPosition() >= 0 && !StringUtils.isEmpty(requestData.getStringValue())) {
            fileDataService.update(new Data(requestData.getStringValue()), requestData.getPosition());
            dataList = fileDataService.getAll();
            result.setCode("200");
            result.setMsg("Value Updated");
            result.setResult(dataList);
        } else {
            result.setCode("406");
            result.setMsg("Value doesn't modified");
            result.setResult(dataList);
        }
        return result;
    }

    /**
     * Method for adding data to list
     *
     * @param requestData Information about the data to be created
     * @return Updated data list
     */
    @JsonView(Views.Public.class)
    @ResponseBody
    @RequestMapping(value = "/data/add", method = RequestMethod.POST)
    public AjaxResponseBody createData(@RequestBody RequestData requestData) {
        log.info("Executing /data/add");
        AjaxResponseBody result = new AjaxResponseBody();
        if (!StringUtils.isEmpty(requestData.getStringValue())) {
            dataList.add(fileDataService.create(new Data(requestData.getStringValue())));
            result.setCode("200");
            result.setMsg("Value added");
            result.setResult(dataList);
        } else {
            result.setCode("406");
            result.setMsg("Value doesn't added");
            result.setResult(dataList);
        }
        return result;
    }

    /**
     * Method for getting all data in list
     *
     * @return Current data list
     */
    @JsonView(Views.Public.class)
    @ResponseBody
    @RequestMapping(value = "/data/getAll")
    public AjaxResponseBody getAll() {
        log.info("Executing /data/getAll");
        AjaxResponseBody result = new AjaxResponseBody();
        dataList = fileDataService.getAll();
        if (dataList != null && dataList.size() > 0) {
            result.setCode("200");
            result.setMsg("");
            result.setResult(dataList);
        } else {
            result.setCode("204");
            result.setMsg("Empty");
            result.setResult(dataList);
        }
        return result;
    }
}