package com.realHenryLover.repository;

import com.realHenryLover.model.School;
import org.apache.commons.collections4.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemorySchoolRepository implements SchoolRepository {
    private final Map<Long, School> store = new HashedMap<>();
    private long sequence = 0;

    @Override
    public School addNewSchool(School school) {
        school.setIndex(sequence);
        store.put(school.getIndex(), school);
        return store.get(sequence++);
    }

    @Override
    public List<School> findAll() {
        return new ArrayList<>(store.values());
    }
}
