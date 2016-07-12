package ua.abond.social.dao.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class TestPageable implements Pageable {
    private int pageNumber;
    private int pageSize;
    private int offset;
    private Sort sort;

    public TestPageable(int pageNumber, int pageSize, int offset, Sort sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.offset = offset;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
