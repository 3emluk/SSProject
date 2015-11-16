package com.ss.zemluk.dal;

import com.ss.zemluk.web.model.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Interface to generalize data access layer
 */
@Component
public interface DataService {
    public Data create(Data data);

    public Data delete(int id);

    public List<Data> getAll();

    public void updateAll(List<Data> dataList);

    public Data getByID(Integer id);

    public Data update(Data data, Integer id);

}
