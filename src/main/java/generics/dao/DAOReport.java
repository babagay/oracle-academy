package generics.dao;

import generics.model.Report;

import java.util.List;

/**
 * Created by student on 11/21/2015.
 */
public class DAOReport implements interfaceDAO<Report> {

    @Override
    public long create(Report newItem) {
        return 0;
    }

    @Override
    public Report read(long id) {
        return null;
    }

    @Override
    public boolean update(Report item) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Report> readAll() {
        return null;
    }
}
