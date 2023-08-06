package com.digiplan.services;

import com.digiplan.entities.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface QueryService {

    Query getQuery(String queryId);

    List<Query> getAllQueries();

    ResponseEntity<Map> addQuery(Query queryData);

    Query updateQuery(String queryId, Query queryData);

    String deleteQuery(String queryId);

}
