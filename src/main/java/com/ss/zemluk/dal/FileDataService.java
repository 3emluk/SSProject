package com.ss.zemluk.dal;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ss.zemluk.web.model.Data;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Service implementation for file service wih JSON
 */
@Service
public class FileDataService implements DataService {

    private final String STORE_PATH = "data.json";

    private Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Override
    public Data create(Data data) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
        List<Data> dataList = null;
        try {
            dataList = getAll();
            dataList.add(data);
            updateAll(dataList);
            log.info("Added data: " + data);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return data;
    }

    @Override
    public Data delete(int id) {
        List<Data> dataList = null;
        Data deletedData = null;
        try {
            dataList = getAll();
            dataList.remove(id);
            updateAll(dataList);
            log.info("Deleted data: " + deletedData);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return deletedData;
    }

    @Override
    public List<Data> getAll() {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<Data> dataList = null;
        try {
            dataList = mapper.readValue(new File(STORE_PATH), new TypeReference<List<Data>>() {
            });
            log.info("Data received");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return dataList;
    }

    @Override
    public Data getByID(Integer id) {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<Data> dataList = null;
        Data data = null;
        try {
            dataList = mapper.readValue(new File(STORE_PATH), new TypeReference<List<Data>>() {
            });
            data = dataList.get(id);
            log.info("Data received successfully");
        } catch (IOException e) {
            log.error(e.getMessage());
            ;
        }
        return data;
    }

    @Override
    public Data update(Data data, Integer id) {
        List<Data> dataList = null;
        try {
            dataList = getAll();
            dataList.set(id, data);
            updateAll(dataList);
            log.info("Data updated successfully: " + data);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return data;
    }

    @Override
    public void updateAll(List<Data> dataList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(STORE_PATH), dataList);
            log.info("Data list updated successfully");
        } catch (JsonGenerationException e) {
            log.error(e.getMessage());
        } catch (JsonMappingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
